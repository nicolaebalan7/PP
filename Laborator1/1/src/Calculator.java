import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

public class Calculator extends JFrame
{
    JButton digits[] = new JButton[10];
    JButton operators[] = new JButton[8];
    String oper_values[] = {"+", "-", "*", "/", "=", "C", "(", ")"};

    JTextArea area = new JTextArea(3, 15);

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
        calculator.setSize(300, 350);
        calculator.setTitle(" Java-Calc, PP Lab1 ");
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Calculator()
    {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(5, 4, 5, 5));

        for (int i=0;i<10;i++)
        {
            digits[i] = new JButton(String.valueOf(i));
            final int finalI = i;
            digits[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    area.append(String.valueOf(finalI));
                }
            });
        }

        for (int i=0;i<8;i++)
        {
            operators[i] = new JButton(oper_values[i]);
            final int finalI = i;
            operators[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    handleOperator(oper_values[finalI]);
                }
            });
        }

        for (int i=1;i<=9;i++) buttonpanel.add(digits[i]);
        buttonpanel.add(operators[0]); // +
        buttonpanel.add(digits[0]);
        buttonpanel.add(operators[1]); // -
        buttonpanel.add(operators[2]); // *
        buttonpanel.add(operators[3]); // /
        buttonpanel.add(operators[6]); // (
        buttonpanel.add(operators[7]); // )
        buttonpanel.add(operators[5]); // C
        buttonpanel.add(operators[4]); // =

        add(buttonpanel, BorderLayout.CENTER);
        area.setEditable(false);
    }

    private void handleOperator(String op)
    {
        if (op.equals("C"))
        {
            area.setText("");
        }
        else if (op.equals("="))
        {
            try
            {
                double result = evaluate(area.getText());
                area.append("\n= " + result);
            } catch (Exception ex)
            {
                area.setText(" Eroare! ");
            }
        }
        else
        {
            area.append(op);
        }
    }

    private int getPrecedence(char op)
    {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }

    public double evaluate(String expression)
    {
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        char[] tokens = expression.toCharArray();

        for (int i=0;i<tokens.length;i++)
        {
            if (tokens[i] == ' ') continue;

            if (Character.isDigit(tokens[i]))
            {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length && Character.isDigit(tokens[i]))
                {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            }
            else if (tokens[i] == '(')
            {
                ops.push('(');
            }
            else if (tokens[i] == ')')
            {
                while (!ops.isEmpty() && ops.peek() != '(')
                {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (!ops.isEmpty()) ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            {
                while (!ops.empty() && getPrecedence(ops.peek()) >= getPrecedence(tokens[i]))
                {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
        {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        return values.pop();
    }

    private double applyOp(char op, double b, double a)
    {
        switch (op)
        {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}