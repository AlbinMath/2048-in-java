import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Game2048 extends JFrame implements KeyListener {
    private static final int SIZE = 4;
    private int[][] board;
    private int score;
    private int bestScore;
    private boolean gameOver;
    private boolean gameWon;
    private static final int WINNING_TILE = 2048;
    private static final Color[] TILE_COLORS = {
        new Color(0xcdc1b4), // 0
        new Color(0xeee4da), // 2
        new Color(0xede0c8), // 4
        new Color(0xf2b179), // 8
        new Color(0xf59563), // 16
        new Color(0xf67c5f), // 32
        new Color(0xf65e3b), // 64
        new Color(0xedcf72), // 128
        new Color(0xedcc61), // 256
        new Color(0xedc850), // 512
        new Color(0xedc53f), // 1024
        new Color(0xedc22e)  // 2048
    };

    public Game2048() {
        setTitle("2048 Game");
        setSize(450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        
        bestScore = 0;
        initGame();
    }
    
    private void initGame() {
        board = new int[SIZE][SIZE];
        score = 0;
        gameOver = false;
        gameWon = false;
        addRandomTile();
        addRandomTile();
    }
    
    private void addRandomTile() {
        if (isBoardFull()) return;
        
        List<Point> emptyCells = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(new Point(i, j));
                }
            }
        }
        
        Point pos = emptyCells.get(new Random().nextInt(emptyCells.size()));
        board[pos.x][pos.y] = new Random().nextInt(10) < 9 ? 2 : 4;
    }
    
    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }
    
    private void moveTiles(Direction direction) {
        if (gameOver || gameWon) return;
        
        int[][] oldBoard = copyBoard();
        
        switch (direction) {
            case UP: moveUp(); break;
            case DOWN: moveDown(); break;
            case LEFT: moveLeft(); break;
            case RIGHT: moveRight(); break;
        }
        
        if (!boardsEqual(oldBoard, board)) {
            addRandomTile();
            checkGameStatus();
            repaint();
        }
    }
    
    private void moveUp() {
        for (int j = 0; j < SIZE; j++) {
            int lastMergePosition = -1;
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    int currentRow = i;
                    while (currentRow > 0) {
                        if (board[currentRow-1][j] == 0) {
                            board[currentRow-1][j] = board[currentRow][j];
                            board[currentRow][j] = 0;
                            currentRow--;
                        } else if (board[currentRow-1][j] == board[currentRow][j] && 
                                  currentRow-1 > lastMergePosition) {
                            board[currentRow-1][j] *= 2;
                            score += board[currentRow-1][j];
                            if (board[currentRow-1][j] == WINNING_TILE) gameWon = true;
                            board[currentRow][j] = 0;
                            lastMergePosition = currentRow-1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void moveDown() {
        for (int j = 0; j < SIZE; j++) {
            int lastMergePosition = SIZE;
            for (int i = SIZE-2; i >= 0; i--) {
                if (board[i][j] != 0) {
                    int currentRow = i;
                    while (currentRow < SIZE-1) {
                        if (board[currentRow+1][j] == 0) {
                            board[currentRow+1][j] = board[currentRow][j];
                            board[currentRow][j] = 0;
                            currentRow++;
                        } else if (board[currentRow+1][j] == board[currentRow][j] && 
                                  currentRow+1 < lastMergePosition) {
                            board[currentRow+1][j] *= 2;
                            score += board[currentRow+1][j];
                            if (board[currentRow+1][j] == WINNING_TILE) gameWon = true;
                            board[currentRow][j] = 0;
                            lastMergePosition = currentRow+1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void moveLeft() {
        for (int i = 0; i < SIZE; i++) {
            int lastMergePosition = -1;
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int currentCol = j;
                    while (currentCol > 0) {
                        if (board[i][currentCol-1] == 0) {
                            board[i][currentCol-1] = board[i][currentCol];
                            board[i][currentCol] = 0;
                            currentCol--;
                        } else if (board[i][currentCol-1] == board[i][currentCol] && 
                                  currentCol-1 > lastMergePosition) {
                            board[i][currentCol-1] *= 2;
                            score += board[i][currentCol-1];
                            if (board[i][currentCol-1] == WINNING_TILE) gameWon = true;
                            board[i][currentCol] = 0;
                            lastMergePosition = currentCol-1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void moveRight() {
        for (int i = 0; i < SIZE; i++) {
            int lastMergePosition = SIZE;
            for (int j = SIZE-2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int currentCol = j;
                    while (currentCol < SIZE-1) {
                        if (board[i][currentCol+1] == 0) {
                            board[i][currentCol+1] = board[i][currentCol];
                            board[i][currentCol] = 0;
                            currentCol++;
                        } else if (board[i][currentCol+1] == board[i][currentCol] && 
                                  currentCol+1 < lastMergePosition) {
                            board[i][currentCol+1] *= 2;
                            score += board[i][currentCol+1];
                            if (board[i][currentCol+1] == WINNING_TILE) gameWon = true;
                            board[i][currentCol] = 0;
                            lastMergePosition = currentCol+1;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private int[][] copyBoard() {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }
    
    private boolean boardsEqual(int[][] b1, int[][] b2) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (b1[i][j] != b2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void checkGameStatus() {
        if (!gameWon) {
            for (int[] row : board) {
                for (int cell : row) {
                    if (cell == WINNING_TILE) {
                        gameWon = true;
                        return;
                    }
                }
            }
        }
        
        gameOver = isGameOver();
        if (score > bestScore) bestScore = score;
    }
    
    private boolean isGameOver() {
        if (!isBoardFull()) return false;
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE-1; j++) {
                if (board[i][j] == board[i][j+1]) return false;
            }
        }
        
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE-1; i++) {
                if (board[i][j] == board[i+1][j]) return false;
            }
        }
        
        return true;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Background
        g.setColor(new Color(0xbbada0));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Score boxes
        int boxWidth = 100;
        int boxHeight = 60;
        int cornerRadius = 5;
        int boxMargin = 10;
        
        // Score box
        int scoreBoxX = 20;
        int scoreBoxY = 20;
        drawScoreBox(g, "SCORE", score, scoreBoxX, scoreBoxY, boxWidth, boxHeight, cornerRadius);
        
        // Best score box
        int bestBoxX = scoreBoxX + boxWidth + boxMargin;
        drawScoreBox(g, "BEST", bestScore, bestBoxX, scoreBoxY, boxWidth, boxHeight, cornerRadius);
        
        // Game board
        int tileSize = 90;
        int margin = 15;
        int boardSize = SIZE * tileSize + (SIZE + 1) * margin;
        int xOffset = (getWidth() - boardSize) / 2;
        int yOffset = 100;
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j];
                int x = xOffset + j * (tileSize + margin) + margin;
                int y = yOffset + i * (tileSize + margin) + margin;
                
                // Tile background
                int colorIndex = value == 0 ? 0 : (int) (Math.log(value) / Math.log(2));
                colorIndex = Math.min(colorIndex, TILE_COLORS.length - 1);
                g.setColor(TILE_COLORS[colorIndex]);
                g.fillRoundRect(x, y, tileSize, tileSize, 10, 10);
                
                // Tile value
                if (value != 0) {
                    g.setColor(value <= 4 ? new Color(0x776e65) : Color.WHITE);
                    Font font = value < 100 ? new Font("Arial", Font.BOLD, 36) : 
                              value < 1000 ? new Font("Arial", Font.BOLD, 32) : 
                                             new Font("Arial", Font.BOLD, 24);
                    g.setFont(font);
                    
                    String s = String.valueOf(value);
                    FontMetrics fm = g.getFontMetrics();
                    int sx = x + (tileSize - fm.stringWidth(s)) / 2;
                    int sy = y + (tileSize - fm.getHeight()) / 2 + fm.getAscent();
                    
                    g.drawString(s, sx, sy);
                }
            }
        }
        
        // Game messages
        if (gameWon) {
            drawCenteredMessage(g, "You Win!", new Color(0xedc22e), 48);
            drawCenteredMessage(g, "Score: " + score, Color.BLACK, 24);
            drawCenteredMessage(g, "Press R to restart", Color.BLACK, 24, 50);
            drawCenteredMessage(g, "Continue playing...", Color.BLACK, 24, 100);
        } else if (gameOver) {
            drawCenteredMessage(g, "Game Over!", Color.RED, 48);
            drawCenteredMessage(g, "Score: " + score, Color.BLACK, 24);
            drawCenteredMessage(g, "Press R to restart", Color.BLACK, 24, 50);
        }
    }
    
    private void drawScoreBox(Graphics g, String label, int value, int x, int y, int width, int height, int cornerRadius) {
        // Box background
        g.setColor(new Color(0xbbada0));
        g.fillRoundRect(x, y, width, height, cornerRadius, cornerRadius);
        
        // Label
        g.setColor(new Color(0xeee4da));
        g.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        int labelX = x + (width - fm.stringWidth(label)) / 2;
        int labelY = y + 20;
        g.drawString(label, labelX, labelY);
        
        // Value
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        fm = g.getFontMetrics();
        String valueStr = String.valueOf(value);
        int valueX = x + (width - fm.stringWidth(valueStr)) / 2;
        int valueY = y + 45;
        g.drawString(valueStr, valueX, valueY);
    }
    
    private void drawCenteredMessage(Graphics g, String text, Color color, int fontSize) {
        drawCenteredMessage(g, text, color, fontSize, 0);
    }
    
    private void drawCenteredMessage(Graphics g, String text, Color color, int fontSize, int yOffset) {
        g.setColor(new Color(255, 255, 255, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent() + yOffset;
        g.drawString(text, x, y);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    moveTiles(Direction.UP); break;
            case KeyEvent.VK_DOWN:  moveTiles(Direction.DOWN); break;
            case KeyEvent.VK_LEFT:  moveTiles(Direction.LEFT); break;
            case KeyEvent.VK_RIGHT: moveTiles(Direction.RIGHT); break;
            case KeyEvent.VK_R:     initGame(); repaint(); break;
            case KeyEvent.VK_ESCAPE: System.exit(0); break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    
    private enum Direction { UP, DOWN, LEFT, RIGHT }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game2048 game = new Game2048();
            game.setVisible(true);
        });
    }
}