package PongPackage;

import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

//finished working game
//testing commit to github..



public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	//variables
	
	private final static Color BACKGROUND_COLOUR =  Color.BLACK;
	private final static int TIMER_DELAY = 5;
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int SCORE_TEXT_X = 100;
	private final static int SCORE_TEXT_Y = 100;
	private final static int SCORE_FONT_SIZE = 50;
	private final static String SCORE_FONT_FAMILY = "Serif";
	boolean gameInitialised = false;
	private final static int POINTS_TO_WIN = 3;
	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	Ball ball;
	Paddle paddle1, paddle2;
	GameState gameState = GameState.Initialising;
	
	//constructor
	public PongPanel() {
		
		
		//sets the JPanel background colour to black
		setBackground(BACKGROUND_COLOUR);
		
		//Timer for game loop
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		
		addKeyListener(this);
		setFocusable(true);
		
	}//end of constructor
	
	
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
		
	}//end of createObjects
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		
		if(gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
			
			
		}
		
		if(gameState == GameState.GameOver) {
			paintWinner(g);
		}
		
	}//end of paintComponent
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.RED);
		g2d.drawLine(getWidth() / 2, 0,	getWidth() / 2, getHeight());
		g2d.dispose();
	}//end of paintDottedLine()

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setYVelocity(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(1);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setYVelocity(-1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYVelocity(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYVelocity(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//calls update()
		update();
		
		//calls repaint()
		repaint();
		
	}
	
	private void update() {
		
		//this runs when the game is first started
		switch(gameState) {
			case Initialising:{
				createObjects();
				gameState = GameState.Playing;
				ball.setXVelocity(BALL_MOVEMENT_SPEED);
				ball.setYVelocity(BALL_MOVEMENT_SPEED);
				
				break;
			}
			case Playing:{
				moveObject(paddle1);  //move player 1 paddle
				moveObject(paddle2);  //move player 2 paddle
				moveObject(ball);  //move the ball
				checkWallBounce();  //check for wall bounce
				checkPaddleBounce();  //check for collision between the ball and player paddles
				checkWin();
				break;
			}
			case GameOver:{
				break;
			}
		}
	}//end of update
	
	
	//paint sprite
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	}//end of paintSprite
	
	
	//moveObjects
	private void moveObject(Sprite sprite) {
		
		sprite.setXPosition(sprite.getXPosition() + sprite.getXVelocity(), getWidth());
		sprite.setYPosition(sprite.getYPosition() + sprite.getYVelocity(), getHeight());
		
		
	}//end of moveObjects
	
	//checkWallBounce
	private void checkWallBounce() {
		if(ball.getXPosition() <= 0) {
			
			//Hit the left of the screen
			addScore(Player.Two);
			resetBall();
		}
		else if(ball.getXPosition() >= getWidth() - ball.getWidth()) {
			
			//Hit the right of the screen
			addScore(Player.One);
			resetBall();
		}
		
		if(ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {
			//Hit the top or bottom of the screen
			ball.setYVelocity(-ball.getYVelocity());
		}
		
	}//end of checkWallBounce
	
	//resets ball position
	private void resetBall() {
		ball.resetToInitialPosition();
	}//end of resetBall()
	
	//checkPaddleBounce
	private void checkPaddleBounce() {
		if(ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
		}
		if(ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setXVelocity(-BALL_MOVEMENT_SPEED);
		}
	}//end of checkPaddleBounce()
	
	private void addScore(Player player) {
		if(player == Player.One) {
			player1Score++;
		}
		else if (player == Player.Two) {
			player2Score++;
		}
	}//end of addScore()
	
	private void checkWin() {
		//player 1 is the winner
		if(player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		}
		
		//player 2 is the winner
		else if(player2Score >= POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
		
	}//end of checkWin()
	
	private void paintScores(Graphics g) {
		
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
		g.drawString(rightScore, getWidth() - SCORE_TEXT_X, SCORE_TEXT_Y);
		
	}//end of paintScores() method
	
	private void paintWinner(Graphics g) {
		String winner = "Win!";
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		
		g.setFont(scoreFont);
		
		if(gameWinner == Player.One) {
			g.drawString(winner, SCORE_TEXT_X, SCORE_TEXT_Y + 100);
		}
		else if(gameWinner == Player.Two) {
			g.drawString(winner, getWidth() - (SCORE_TEXT_X * 2), SCORE_TEXT_Y + 100);
		}
		
	}//end of paintWinner()
	
	//welcome to the end of the world

}
