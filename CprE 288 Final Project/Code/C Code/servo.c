#include "stdbool.h"
#include "math.h"
#include "lcd.h"
#include "timer.h"
#include "movement.h"
#include "servo.h"
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"

volatile unsigned int match_value;

#define cybot6Slope -151.67
#define cybot6Intercept 310600

#define cybot7Slope -150
#define cybot7Intercept 312100

#define cybot11Slope -155.56
#define cybot11Intercept 311000

void servo_init(void)
{
    SYSCTL_RCGCGPIO_R |= 0x2;
    SYSCTL_RCGCTIMER_R |= 0x2;

    GPIO_PORTB_DEN_R |= 0x20;                   // set pin 5
    GPIO_PORTB_DIR_R |= 0x20;                   // set pin 5 output
    GPIO_PORTB_AFSEL_R |= 0x20;                 // turning on alternative function
    GPIO_PORTB_PCTL_R |= 0x700000;              // enable TX and RX on pin 3

    TIMER1_CTL_R = ~0x100;

    TIMER1_TBMR_R |= 0b1010;                    // periodic and PWM enable
    TIMER1_CFG_R = TIMER_CFG_16_BIT;            // set size of timer to 16

    TIMER1_TBILR_R = 320000 & 0xFFFF;           // lower 16 bits of the interval
    TIMER1_TBPR_R = 320000 & 0xFF000000;        // set the upper 8 bits of the interval

    TIMER1_TBMATCHR_R = match_value & 0xFFFF;
    TIMER1_TBPMR_R = match_value & 0xFF000000;

    TIMER1_CTL_R = 0x100;
}

void servo_move(double degrees)
{
    if (degrees >= 0 && degrees <= 180)
    {
        match_value = (cybot11Slope * degrees) + cybot11Intercept;
        servo_init();
    }
}
