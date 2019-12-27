#include "math.h"
#include "lcd.h"
#include "timer.h"
#include "servo.h"
#include "cyBot_Sonar.h"
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"
#include "uart.h"
#include "adc.h"
#include <stdbool.h>

/**
 * Scans the surroundings for obstacles.
 *
 * Authors : Daniel Nikolic
 *         : Ahmad Alramahi
 *         : Rithwik Gokhale
 *         : Hayden Sellars
 */

// MACROS

#define cybot6Coeff 97354
#define cybot6Exp -1.158

#define cybot5Coeff 117164
#define cybot5Exp -1.184

#define cybot8Coeff 9549.6
#define cybot8Exp -0.927

#define cybot11Coeff 157709
#define cybot11Exp -1.213

#define cybot4Coeff 92443
#define cybot4Exp -1.145

#define cybot7Coeff 89950
#define cybot7Exp -1.134

// STRUCTS

typedef struct object_struct
{
    double distance;
    double location;
    double radialSize;
    double linearWidth;
    int index;
}   object;

typedef struct data_struct
{
    double ir_distance;
    int ping_distance;
    int angle;
}   data;


void scan(void)
{

    uart_init();
    lcd_init();
    adc_init();
    TIMER3B_init();

    object holder[45];

    int sensor = 0;
    double distance = 0;
    int i = 0;
    char sendstr[180];
    data data_holder[180];

    int g = 0;
    for (i = 0; i <= 180; i += 1)
    {
        data_holder[g].angle = i;
        servo_move(i);
        if(i == 0)
        {
            timer_waitMillis(1500);
        }
        timer_waitMillis(50);
        sensor = adc_read();
        distance = cybot11Coeff * pow(sensor, cybot11Exp);
        data_holder[g].ir_distance = distance;
        data_holder[g].ping_distance = ping_read();

        g++;
    }

    int j = 0;
    int smallestValue = 100000; // initialized to garbage value so that the final value is always accurate
    int start = 0;
    int k = 0;
    int flag = 0;
    int y = 0;

    for (j = 1; j <= 180; j++) // iterates through every scanned degree to find objects detected
    {
        if ((data_holder[j].ir_distance <= 50 && data_holder[j].ping_distance <= 50 && flag == 0)) // first edge of object detected
        {
            flag = 1;
            start = j;
        }
        else if((data_holder[j].ir_distance > 50 && data_holder[j].ping_distance > 50 && flag == 1) || (j == 180 && flag == 1)) // finds width, location, and distance of object
        {
            y++;
            flag = 0;
            holder[y - 1].index = y;

            if (data_holder[start].ping_distance > 0)
            {
                smallestValue = data_holder[start].ping_distance;
            }
            for (k = start + 1; k <= j - 1; k++)
            {
                if (data_holder[k].ping_distance < smallestValue)
                {
                    if (data_holder[k].ping_distance > 0)
                    {
                        smallestValue = data_holder[k].ping_distance; // gets the most accurate distance from cyBOT to object
                    }
                }
            }
            holder[y - 1].radialSize = j - start;
            holder[y - 1].location = (((j - 1)) + (start)) / 2 - 17;
            holder[y - 1].distance = smallestValue;
            holder[y - 1].linearWidth = ((double) holder[y - 1].radialSize / 180.0) * ((3.14) * (double) holder[y - 1].distance);

            // a little bit of hard coding to meet our mapping specifications

            if (holder[y - 1].linearWidth < 14)
            {
                 holder[y - 1].linearWidth = 3;
            }
            else
            {
                holder[y - 1].linearWidth = 11;
            }
            if (holder[y - 1].linearWidth < 12)
            {
                sprintf(sendstr, "%d %d %d", smallestValue, (int) holder[y - 1].location, (int) holder[y - 1].linearWidth);
                uart_sendStr(sendstr);
            }
        }
    }
    uart_sendStr("e"); // ends the scan
}
