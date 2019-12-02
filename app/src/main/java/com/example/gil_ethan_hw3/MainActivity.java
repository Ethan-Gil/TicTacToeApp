/*
        Assignment 3:   Tic Tac Toe
        Course:         CSC 415
        Author:         Ethan Gil
        Date:           12/2/2019
 */

package com.example.gil_ethan_hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mGameStatus;   // Will display the status of the game

    private char[] mBoard;          // Character array to represent the board
    private Button[] mGameButtons;  // Button array that the player will pressed (text will change when this occurs)

    private Button newGameButton;   // Button to begin a new game

    boolean playerWon;
    boolean computerWon;
    boolean tie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the status
        mGameStatus = findViewById(R.id.mGameStatus);

        // Initializing character array to represent field buttons
        mBoard = new char[9];

        // Initializing the field buttons
        mGameButtons = new Button[9];
        mGameButtons[0] = findViewById(R.id.btn0);
        mGameButtons[1] = findViewById(R.id.btn1);
        mGameButtons[2] = findViewById(R.id.btn2);
        mGameButtons[3] = findViewById(R.id.btn3);
        mGameButtons[4] = findViewById(R.id.btn4);
        mGameButtons[5] = findViewById(R.id.btn5);
        mGameButtons[6] = findViewById(R.id.btn6);
        mGameButtons[7] = findViewById(R.id.btn7);
        mGameButtons[8] = findViewById(R.id.btn8);

        newGameButton = findViewById(R.id.newGameBtn);

        // Start a new game
        newGame();

        // Play the game
        playGame();

    }

    /** Creates a new game by initializing mGameBoard */
    private void newGame() {

        // Initializing all values to empty / Clearing the board
        for (int i = 0; i < mBoard.length; i++) {
            mBoard[i] = ' ';
            mGameButtons[i].setText("");
        }

        mGameStatus.setText("Playing");

        playerWon = false;
        computerWon = false;
        tie = false;
    }

    /** Simulates the game loop */
    private void playGame() {

        // Creates a new game when clicked by calling newGame()
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        // The Buttons that will be pressed by the user.
        // A Button can not be pressed more than once and it may not be pressed after it has text.
        // A Button can not be pressed when the game is over
        mGameButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[0] == ' ' && !isGameOver())
                    onPlayButtonClick(0);
            }
        });

        mGameButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[1] == ' ' && !isGameOver())
                    onPlayButtonClick(1);
            }
        });

        mGameButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[2] == ' ' && !isGameOver())
                    onPlayButtonClick(2);
            }
        });

        mGameButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[3] == ' ' && !isGameOver())
                    onPlayButtonClick(3);
            }
        });

        mGameButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[4] == ' ' && !isGameOver())
                    onPlayButtonClick(4);            }
        });

        mGameButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[5] == ' ' && !isGameOver())
                    onPlayButtonClick(5);            }
        });

        mGameButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[6] == ' ' && !isGameOver())
                    onPlayButtonClick(6);            }
        });

        mGameButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[7] == ' ' && !isGameOver())
                    onPlayButtonClick(7);            }
        });

        mGameButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoard[8] == ' ' && !isGameOver())
                    onPlayButtonClick(8);            }
        });

    }

    /** Sets the player's move */
    private void setPlayerMove(int index) {
        mBoard[index] = 'X';
        mGameButtons[index].setText("X");
        mGameButtons[index].setTextColor(getResources().getColor(R.color.red));
    }

    /** For getting the computer's randomly chosen move */
    private void getComputerMove() {

        Random rand = new Random();
        int index;

        // Find an available index
        while (true) {
            index = rand.nextInt(9);    // Generate random number from 0 to 8

            // If the spot index of the array is empty, then it will be used by the computer
            if (mBoard[index] == ' ') {
                mBoard[index] = 'O';
                mGameButtons[index].setText("O");
                mGameButtons[index].setTextColor(getResources().getColor(R.color.cyan));
                break;
            }
        }
    }

    /** Returns an integer indicating if there is a winner, tie game, or more moves to be made */
    // Returns 0 if there is a winner
    // Returns 1 if there is a tie
    // Returns 2 if the game should continue
    // Returns -1 if there is an error.
    private int checkForWinner() {

        // Checking to see if there is a winner
        if (checkBoard())
            return 0;

        // Checking to see if there was a tie
        else if (isFull())
            return 1;

        // Checking to see if the game should continue
        else if (!isFull())
            return 2;

        return -1; // should never happen
    }

    /** Returns true if there is a winner (three of the same characters in a row) */
    private boolean checkBoard() {

        return (mBoard[0] == mBoard[1] && mBoard[0] == mBoard[2] && mBoard[2] != ' ' ||     // First Row
                mBoard[3] == mBoard[4] && mBoard[3] == mBoard[5] && mBoard[5] != ' ' ||     // Second Row
                mBoard[6] == mBoard[7] && mBoard[6] == mBoard[8] && mBoard[8] != ' ' ||     // Third Row
                mBoard[0] == mBoard[3] && mBoard[0] == mBoard[6] && mBoard[6] != ' ' ||     // First Column
                mBoard[1] == mBoard[4] && mBoard[1] == mBoard[7] && mBoard[7] != ' ' ||     // Second Column
                mBoard[2] == mBoard[5] && mBoard[2] == mBoard[8] && mBoard[8] != ' ' ||     // Third Column
                mBoard[0] == mBoard[4] && mBoard[0] == mBoard[8] && mBoard[8] != ' ' ||     // Horizontal Left to Right
                mBoard[2] == mBoard[4] && mBoard[2] == mBoard[6] && mBoard[6] != ' ');      // Horizontal Right to Left
    }

    /** Returns true if the field is full */
    private boolean isFull() {
        boolean full = true;

        for (char c : mBoard) {
            if (c == ' ')
                full = false;
        }

        return full;
    }

    /** Called when the user clicks on a game board button */
    private void onPlayButtonClick(int index) {

        // The player selects the specified button
        setPlayerMove(index);

        // Check to see if the player won
        if (checkForWinner() == 0) {
            playerWon = true;
            mGameStatus.setText("You Won!");
        }

        // Check for a tie
        else if (checkForWinner() == 1) {
            tie = true;
            mGameStatus.setText("There was a tie!");
        }

        // Otherwise, continue the game
        else if (checkForWinner() == 2) {
            getComputerMove();

            // Check to see if the computer won
            if (checkForWinner() == 0) {
                computerWon = true;
                mGameStatus.setText("The Computer Won!");
            }

            // Check for a tie
            else if (checkForWinner() == 1) {
                tie = true;
                mGameStatus.setText("There was a tie!");
            }
        }
    }

    /** Returns true if the game should be over */
    private boolean isGameOver() {
        return playerWon || computerWon || tie;
    }

}
