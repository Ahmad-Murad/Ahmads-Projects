#include <stdint.h>
#include <stdbool.h>
#include "timer.h"
#include "lcd.h"
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"

char banner[21];
int i = 0;

void uart_init(void)
{
    SYSCTL_RCGCGPIO_R |= SYSCTL_RCGCGPIO_R1;
    SYSCTL_RCGCUART_R |= SYSCTL_RCGCUART_R1;
    GPIO_PORTB_AFSEL_R |= (BIT0 | BIT1);
    GPIO_PORTB_PCTL_R |= 0x00000011;
    GPIO_PORTB_DEN_R |= (BIT0 | BIT1);
    GPIO_PORTB_DIR_R &= ~BIT0;
    GPIO_PORTB_DIR_R |= BIT1;

    uint16_t iBRD = 8;
    uint16_t fBRD = 44;

    UART1_CTL_R &= ~(UART_CTL_UARTEN);
    UART1_IBRD_R = iBRD;
    UART1_FBRD_R = fBRD;
    UART1_LCRH_R = UART_LCRH_WLEN_8 ;
    UART1_CTL_R = (UART_CTL_RXE | UART_CTL_TXE | UART_CTL_UARTEN);
}

void uart_sendChar(char data)
{
    while(UART1_FR_R & 0x20)
   {
        // wait
   }

    // send data
    UART1_DR_R = data;
}

char uart_receive(void)
{
    char data = 0;
    while(UART1_FR_R & UART_FR_RXFE)
    {
        // wait
    }

    // mask the 4 error bits and grab only 8 data bits
    data = (char)(UART1_DR_R & 0xFF);

    return data;
}

void UART1_Handler(void)
{
    // received a byte
    if (UART1_MIS_R & UART_MIS_RXMIS)
    {
        char ch = uart_receive();
        if (ch != 0x00 && i <= 19 && ch != 0x0D)
        {
            banner[i] = ch;
            i++;
            uart_sendChar(ch);
        }
        if (i == 20 || ch == 0x0D)
        {
            banner[i] = '\0';
            lcd_printf("%s", banner);
            i = 0;
            uart_sendChar('\r');
            uart_sendChar('\n');
        }
        UART1_ICR_R |= UART_ICR_RXIC; // clear interrupt
    }
    // sent a byte
    else if (UART1_MIS_R & UART_MIS_TXMIS)
    {
        char ch = uart_receive();
        if (ch != 0x00 && i <= 19 && ch != 0x0D)
        {
            banner[i] = ch;
            i++;
            uart_sendChar(ch);
        }
        if (i == 20 || ch == 0x0D)
        {
            banner[i] = '\0';
            lcd_printf("%s", banner);
            i = 0;
            uart_sendChar('\r');
            uart_sendChar('\n');
        }
        UART1_ICR_R |= UART_ICR_TXIC; // clear interrupt
    }
}

void uart_interrupt_init(void)
{
     UART1_CTL_R &= ~(UART_CTL_UARTEN);
     UART1_ICR_R = (UART_ICR_TXIC | UART_ICR_RXIC);
     UART1_IM_R |= UART_IM_TXIM | UART_IM_RXIM;
     NVIC_PRI1_R |= 0x00200000;
     NVIC_EN0_R |= 0x00000040;
     IntRegister(INT_UART1, UART1_Handler);
     IntMasterEnable();

     UART1_CTL_R = (UART_CTL_RXE | UART_CTL_TXE | UART_CTL_UARTEN);
}

void uart_sendStr(const char *data)
{
    while (*data != '\0')
    {
        uart_sendChar(*data);
        data++;
    }
}
