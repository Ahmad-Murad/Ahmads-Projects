#include "lcd.h"
#include "timer.h"
#include "movement.h"
#include "servo.h"
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"
#include "uart.h"
#include "adc.h"
#include "servo.h"
#include "cliff_detect.h"
#include "open_interface.h"
#include "music.h"
#include "scan.h"
#include "movement.h"
#include <stdbool.h>

/**
 * The main function for the final project.
 *
 * Authors : Daniel Nikolic
 *         : Ahmad Alramahi
 *         : Rithwik Gokhale
 *         : Hayden Sellars
 */

int main(void)
{
    lcd_init();
    uart_init();

    int counter = 0;

    int start = 0;
    char ch;
    while (1)
    {
        ch = uart_receive();
        if (ch == 'x' && start == 0)
        {
            start = 1;
            //music2();
        }
        if (start == 1)
        {
            if (ch == 'w')
            {
                move_forward(16);
                counter++;
            }
            else if (ch == 'a')
            {
                turn_left(8);
            }
            else if (ch == 'd')
            {
                turn_right(8);
            }
            else if (ch == 'j')
            {
                scan();
            }
			else if (ch == 'm')
			{
				//music1();
			}
            else if (ch == 'p')
            {
                lcd_printf("pizza delivered");
				//music1();
            }
        }

        if (counter == 4)
        {
			turn_right(1);
			counter = 0;
        }
    }
}
