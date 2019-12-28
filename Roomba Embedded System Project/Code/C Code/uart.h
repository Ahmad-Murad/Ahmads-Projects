#ifndef UART_H_
#define UART_H_

void uart_init(void);
void uart_sendChar(char data);
char uart_receive(void);
void uart_interrupt_init(void);
void UART1_Handler(void);
void uart_sendStr(const char *data);

#endif /* UART_H_ */
