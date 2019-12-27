#include <open_interface.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "Timer.h"
#include "lcd.h"
#include <inc/tm4c123gh6pm.h>
#include "cliff_detect.h"
#include "movement.h"
#include "uart.h"
#include <stdbool.h>

/**
 * All necessary movement controls.
 *
 * Authors : Daniel Nikolic
 *         : Ahmad Alramahi
 *         : Rithwik Gokhale
 *         : Hayden Sellars
 */

void move_forward(float millimeters)
{
	uart_init();
	
    oi_t *sensor = oi_alloc();
    oi_init(sensor);

    int front_left;
    int front_right;
    int left;
    int right;
	
    
    float sum = 0;
    char sendstr[4];
    char var;
    oi_setWheels(50, 50); // initial speed
    int error = 0;
	
    while (sum < millimeters)
    {
        cliff_detect(&front_left, &front_right, &left, &right);

        if (front_right >= 2700 || front_left >= 2700 || left >= 2700 || right >= 2700) // TAPE DETECT
        {
            var = 't';
            sprintf(sendstr, "%c%d", var, (int) sum / 10);
            uart_sendStr(sendstr);
            error = 1;
            oi_setWheels(0, 0);
            oi_update(sensor);
            move_backwards(10);
            turn_right(65);
            break;
        }
        else if (front_right <= 700 || front_left <= 700 || left <= 700 || right <= 700) // CLIFF DETECT
        {
            var = 'c';
            sprintf(sendstr, "%c%d", var, (int) sum / 10);
            uart_sendStr(sendstr);
            error = 1;
            oi_setWheels(0, 0);
            oi_update(sensor);
            move_backwards(10);
            turn_right(65);
            break;
        }

        if (sensor -> bumpLeft)
        {
            var = 'l';
            sprintf(sendstr, "%c%d", var, (int) sum / 10);
            uart_sendStr(sendstr);
            error = 1;
            move_backwards(10);
            turn_right(65);
            break;
        }
        else if (sensor -> bumpRight)
        {
            var = 'r';
            sprintf(sendstr, "%c%d", var, (int) sum / 10);
            uart_sendStr(sendstr);
            error = 1;
            move_backwards(10);
            turn_left(65);
            break;
        }
        oi_update(sensor);
        sum += sensor -> distance; // updates distance
    }

    if (error == 0) // all clear
    {
        var = 'g';
        sprintf(sendstr, "%c%d", var, 5);
        uart_sendStr(sendstr);
    }

    oi_setWheels(0, 0); // stop

    oi_free(sensor);
}

void move_backwards(float millimeters)
{
    oi_t *sensor = oi_alloc();
    oi_init(sensor);

    float sum = millimeters;

    oi_setWheels(-100, -100); // initial speed

    while (sum > 0)
    {
        oi_update(sensor);
        sum += sensor -> distance; // updates distance in the backwards direction
    }

    oi_setWheels(0, 0); // stop

    oi_free(sensor);
}

void turn_right(float degrees)
{
    oi_t *sensor = oi_alloc();
    oi_init(sensor);

    float sum = 0;

    oi_setWheels(-50, 50); // initial angle rate

    while (sum < degrees)
    {
        oi_update(sensor);
        sum += -(sensor -> angle); // updates angle turn
    }

    oi_setWheels(0, 0); // stop

    oi_free(sensor);
}

void turn_left(float degrees)
{
    oi_t *sensor = oi_alloc();
    oi_init(sensor);

    float sum = 0;

    oi_setWheels(50, -50); // initial angle rate

    while (sum < degrees)
    {
        oi_update(sensor);
        sum += sensor -> angle; // updates angle turn
    }

    oi_setWheels(0, 0); // stop

    oi_free(sensor);
}
