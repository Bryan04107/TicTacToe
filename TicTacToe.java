import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
    private JButton[][] buttons;
    private JLabel message;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        message = new JLabel("Player X's turn");
        Font font = new Font("Arial", Font.BOLD, 50);
        message.setFont(font);
        add(message, BorderLayout.NORTH);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                board.add(buttons[i][j]);
            }
        }
        add(board, BorderLayout.CENTER);

        JButton restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 30));
        restartButton.addActionListener(new RestartButtonListener());
        add(restartButton, BorderLayout.SOUTH);

        currentPlayer = 'X';
        gameOver = false;
        setVisible(true);
    }


    //Having A Breakdown Over Victory Text
    private void winnerText() {
        if (currentPlayer == 'X') {
            message.setText("Player O wins!");
        } else {
            message.setText("Player X wins!");
        }
    }


    private void checkGame() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") && buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())) {
                gameOver = true;
                buttons[i][0].setBackground(Color.GREEN);
                buttons[i][1].setBackground(Color.GREEN);
                buttons[i][2].setBackground(Color.GREEN);
                winnerText();
                return;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().equals("") && buttons[0][j].getText().equals(buttons[1][j].getText())
                    && buttons[1][j].getText().equals(buttons[2][j].getText())) {
                gameOver = true;
                buttons[0][j].setBackground(Color.GREEN);
                buttons[1][j].setBackground(Color.GREEN);
                buttons[2][j].setBackground(Color.GREEN);
                winnerText();
                return;
            }
        }
        if (!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())) {
            gameOver = true;
            buttons[0][0].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][2].setBackground(Color.GREEN);
            winnerText();
            return;
        }
        if (!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())) {
            gameOver = true;
            buttons[0][2].setBackground(Color.GREEN);
            buttons[1][1].setBackground(Color.GREEN);
            buttons[2][0].setBackground(Color.GREEN);
            winnerText();
            return;
        }
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                }
            }
        }
        if (draw) {
            gameOver = true;
            message.setText("It's a tie!");
        }
    }


    private class ButtonListener implements ActionListener {
        private int row;
        private int col;
        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public void actionPerformed(ActionEvent e) {
            if (gameOver) {
                return;
            }
            if (!buttons[row][col].getText().equals("")) {
                return;
            }
            buttons[row][col].setText("" + currentPlayer);
            if (currentPlayer == 'X') {
                currentPlayer = 'O';
                message.setText("Player O's turn");
            } else {
                currentPlayer = 'X';
                message.setText("Player X's turn");
            }
            checkGame();
        }
    }


    private class RestartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentPlayer = 'X';
            gameOver = false;
            message.setText("Player X's turn");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(null);
                }
            }
        }
    }


    public static void main(String[] args) {
        new TicTacToe();
    }
}
