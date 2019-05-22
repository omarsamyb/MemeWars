package view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;

import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.NonActivatablePowerHero;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

@SuppressWarnings("serial")
public class GameView extends JFrame implements ActionListener {
	
	//private Container mainWindow;

	private Game game;
	private Player player1;
	private Player player2;
	
	private JPanel player1pos;
	private JPanel player2pos;
	private JPanel board;
	private JPanel button;
	private JPanel button1;
	private JPanel button2;
	private JPanel button3;
	private JPanel cpanel;
	private JPanel currentPanel;
	private JPanel action;
	private JPanel action_list;
	private JPanel board_control;
	private JPanel deadPanel;
	private JPanel dead_quit;
	private JPanel pos1WithIcon;
	private JPanel pos2WithIcon;
	
	private JLabel currentLabel;
	private JLabel colorLabelp1[];
	private JLabel colorLabelp2[];
	private JLabel pos1Icon;
	private JLabel pos2Icon;
	
	private JButton l;
	private JButton u;
	private JButton d;
	private JButton r;
	private JButton dl;
	private JButton dr;
	private JButton ul;
	private JButton ur;
	private JButton move;
	private JButton usepower;
	private JButton heroB;
	private JButton powerOnPieceB;
	private JButton selectedPieceB;
	private JButton pieceNullB;
	private JButton directionB;
	private JButton cells[][];
	private JButton quit;
	
	private Point newPos;
	private Direction direction;
	
	private Piece heroP;
	private Piece powerOnPieceP;
	private Piece selectedPieceP;
	private Piece tempPiece;
	private Piece medicTargetPiece;
	private Piece d1;
	private Piece d2;
	
	private JComboBox<Piece> deadP1;
	private JComboBox<Piece> deadP2;
	
	private AudioInputStream audioInputStream;
	private Clip clip;
	private Clip clipBackMusic;
	
	private Point initialClick;
	private JFrame mainWindow;
	
	public GameView() {
		super();
		mainWindow=new JFrame();
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setUndecorated(true);
		mainWindow.setTitle("Meme Wars Chess Game");
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setBounds(400, 0, 900, 900);
		mainWindow.setBackground(Color.BLACK);
		
		JOptionPane.showMessageDialog(null, "Please use the Player 1 or Player 2 score Bars to drag the window if it's not fitted in your monitor");
		String x=(String) JOptionPane.showInputDialog(null, "Enter Team Red's Player Name", "Meme Wars Chess Game", JOptionPane.INFORMATION_MESSAGE, null, null, null);
		String y=(String) JOptionPane.showInputDialog(null, "Enter Team Green's Player Name", "Meme Wars Chess Game", JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if(x==null)
			x="Player 1";
		if(y==null)
			y="Player 2";
		if(x.equals(""))
			x="Player 1";
		if(y.equals(""))
			y="Player 2";
		
		player1=new Player(x);
		player2=new Player(y);
		game= new Game(player1, player2);
		
		try {
	        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("play.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	    }
		
		//  Directional Buttons  //
		r=new JButton();r.setToolTipText("Right");r.setActionCommand("R");;r.addActionListener(this);
		r.setBorder(BorderFactory.createEmptyBorder());
		r.setIcon(new ImageIcon(getClass().getResource("R.png")));
		l=new JButton();l.setToolTipText("Left");l.setActionCommand("L");l.addActionListener(this);
		l.setBorder(BorderFactory.createEmptyBorder());
		l.setIcon(new ImageIcon(getClass().getResource("L.png")));
		u=new JButton();u.setToolTipText("Up");u.setActionCommand("U");u.addActionListener(this);
		u.setBorder(BorderFactory.createEmptyBorder());
		u.setIcon(new ImageIcon(getClass().getResource("U.png")));
		d=new JButton();d.setToolTipText("Down");d.setActionCommand("D");d.addActionListener(this);
		d.setBorder(BorderFactory.createEmptyBorder());
		d.setIcon(new ImageIcon(getClass().getResource("D.png")));
		dl=new JButton();dl.setToolTipText("DownLeft");dl.setActionCommand("DL");dl.addActionListener(this);
		dl.setBorder(BorderFactory.createEmptyBorder());
		dl.setIcon(new ImageIcon(getClass().getResource("DL.png")));
		dr=new JButton();dr.setToolTipText("DownRight");dr.setActionCommand("DR");dr.addActionListener(this);
		dr.setBorder(BorderFactory.createEmptyBorder());
		dr.setIcon(new ImageIcon(getClass().getResource("DR.png")));
		ul=new JButton();ul.setToolTipText("UpLeft");ul.setActionCommand("UL");ul.addActionListener(this);
		ul.setBorder(BorderFactory.createEmptyBorder());
		ul.setIcon(new ImageIcon(getClass().getResource("UL.png")));
		ur=new JButton();ur.setToolTipText("UpRight");ur.setActionCommand("UR");ur.addActionListener(this);
		ur.setBorder(BorderFactory.createEmptyBorder());
		ur.setIcon(new ImageIcon(getClass().getResource("UR.png")));
		button1= new JPanel();
		button1.setLayout(new BorderLayout());
		button1.add(u,BorderLayout.CENTER);
		button1.add(ur,BorderLayout.EAST);
		button1.add(ul,BorderLayout.WEST);
		
		button2= new JPanel();
		button2.setLayout(new GridLayout(1, 2));
		button2.add(l);
		button2.add(r);
		
		button3=new JPanel();
		button3.setLayout(new BorderLayout());
		button3.add(d,BorderLayout.CENTER);
		button3.add(dl,BorderLayout.WEST);
		button3.add(dr,BorderLayout.EAST);
		
		button= new JPanel();
		button.setLayout(new GridLayout(3, 1));
		button.add(button1);
		button.add(button2);
		button.add(button3);
		button.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0),4,false));
		
		//  Current Player  //
		ImageIcon memewars = new ImageIcon(getClass().getResource("title.png"));
		currentLabel =new JLabel(x,memewars,JLabel.CENTER);
		currentLabel.setBackground(new Color(16, 17, 17));
		currentLabel.setOpaque(true);
		currentLabel.setFont(new Font("Courier",Font.BOLD, 35));
		currentLabel.setForeground(Color.RED);
		currentLabel.setHorizontalTextPosition(JLabel.CENTER);
		currentLabel.setVerticalTextPosition(JLabel.BOTTOM);
		currentLabel.setIconTextGap(0);
		
		currentPanel =new JPanel();
		currentPanel.setLayout(new GridLayout(1,1));
		currentPanel.setPreferredSize(new Dimension(452, 113));
		currentPanel.setMaximumSize(new Dimension(452, 113));
		currentPanel.setBorder(BorderFactory.createEmptyBorder(-25, 0, 0, 0));
		currentPanel.add(currentLabel);
		
		
		//  Move & Use power  //
		move= new JButton();
		move.setActionCommand("Move");
		move.setToolTipText("Move");
		move.addActionListener(this);
		move.setBorder(BorderFactory.createEmptyBorder());
		move.setIcon(new ImageIcon(getClass().getResource("move.png")));
		
		usepower= new JButton();
		usepower.setActionCommand("Use Power");
		usepower.setToolTipText("Use Power");
		usepower.addActionListener(this);
		usepower.setBorder(BorderFactory.createEmptyBorder());
		usepower.setIcon(new ImageIcon(getClass().getResource("usepower.png")));
		action=new JPanel();
		action.setLayout(new GridLayout(1, 2));
		action.add(move);
		action.add(usepower);
		action.setMaximumSize(new Dimension(192, 52));
		action.setPreferredSize(new Dimension(192, 52));
		
		// Dead and quit  //
		deadP1=new JComboBox<>();
		deadP1.setToolTipText(x+" Dead Pieces");
		deadP1.addActionListener(this);
		deadP1.setBackground(Color.BLACK);
		deadP1.setForeground(Color.RED);
		deadP1.setMaximumSize(new Dimension(96, 26));
		deadP1.setPreferredSize(new Dimension(96, 26));
		deadP2=new JComboBox<>();
		deadP2.setToolTipText(y+" Dead Pieces");
		deadP2.addActionListener(this);
		deadP2.setBackground(Color.BLACK);
		deadP2.setForeground(Color.GREEN);
		deadP2.setMaximumSize(new Dimension(96, 26));
		deadP2.setPreferredSize(new Dimension(96, 26));
		
		
		deadPanel = new JPanel();
		deadPanel.setLayout(new GridLayout(2, 1));
		deadPanel.add(deadP1);
		deadPanel.add(deadP2);
		
		quit=new JButton();
		quit.setToolTipText("Quit");
		quit.setActionCommand("Quit");
		quit.setBorder(BorderFactory.createEmptyBorder());
		quit.addActionListener(this);
		quit.setIcon(new ImageIcon(getClass().getResource("Q.png")));
		
		dead_quit=new JPanel();
		dead_quit.setLayout(new GridLayout(1,2));
		dead_quit.add(quit);
		dead_quit.add(deadPanel);
		
		
		action_list=new JPanel();
		action_list.setLayout(new GridLayout(2,1));
		action_list.add(action);
		action_list.add(dead_quit);
		action_list.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0),4,false));
		action_list.setMaximumSize(new Dimension(200, 113));
		action_list.setPreferredSize(new Dimension(200, 113));
		
		// Control Panel  //
		cpanel= new JPanel();
		cpanel.setLayout(new BorderLayout());
		cpanel.add(button,BorderLayout.WEST);
		cpanel.add(currentPanel,BorderLayout.CENTER);
		cpanel.add(action_list,BorderLayout.EAST);
		cpanel.setPreferredSize(new Dimension(900, 113));
		mainWindow.add(cpanel, BorderLayout.SOUTH);
		
		//  Player 1 PosTarget  //
		
		player1pos=new JPanel();
		player1pos.setLayout(new GridLayout(6, 1));
		player1pos.setPreferredSize(new Dimension(50,840));
		colorLabelp1=new JLabel[6];
		for(int i = 0;i<6;i++) {
			colorLabelp1[i]=new JLabel();
			colorLabelp1[i].setBackground(new Color(79, 79, 79));
			colorLabelp1[i].setIcon(new ImageIcon(getClass().getResource("emptyscore.png")));
			player1pos.add(colorLabelp1[i]);
			colorLabelp1[i].setOpaque(true);
		}
		pos1Icon = new JLabel();
		pos1Icon.setBackground(Color.BLACK);
		pos1Icon.setOpaque(true);
		pos1Icon.setPreferredSize(new Dimension(50, 60));
		pos1Icon.setIcon(new ImageIcon(getClass().getResource("score1.png")));
		
		pos1WithIcon = new JPanel();
		pos1WithIcon.setLayout(new BorderLayout());
		pos1WithIcon.add(player1pos,BorderLayout.NORTH);
		pos1WithIcon.add(pos1Icon,BorderLayout.CENTER);
		pos1WithIcon.addMouseListener(new mouseClickOnPanel());
		pos1WithIcon.addMouseMotionListener(new mouseDrag());
		mainWindow.add(pos1WithIcon,BorderLayout.EAST);
		
			
		//  Player 2 PosTarget  //
		player2pos=new JPanel();
		player2pos.setLayout(new GridLayout(6, 1));
		player2pos.setPreferredSize(new Dimension(50, 840));
		colorLabelp2=new JLabel[6];
		for(int i = 0;i<6;i++) {
			colorLabelp2[i]=new JLabel();
			colorLabelp2[i].setBackground(new Color(79, 79, 79));
			colorLabelp2[i].setIcon(new ImageIcon(getClass().getResource("emptyscore.png")));
			player2pos.add(colorLabelp2[i]);
			colorLabelp2[i].setOpaque(true);
		}
		pos2Icon = new JLabel();
		pos2Icon.setBackground(Color.BLACK);
		pos2Icon.setOpaque(true);
		pos2Icon.setPreferredSize(new Dimension(50, 60));
		pos2Icon.setIcon(new ImageIcon(getClass().getResource("score2.png")));
		
		pos2WithIcon = new JPanel();
		pos2WithIcon.setLayout(new BorderLayout());
		pos2WithIcon.add(player2pos,BorderLayout.NORTH);
		pos2WithIcon.add(pos2Icon,BorderLayout.CENTER);
		pos2WithIcon.addMouseListener(new mouseClickOnPanel());
		pos2WithIcon.addMouseMotionListener(new mouseDrag());
		mainWindow.add(pos2WithIcon,BorderLayout.WEST);
		
		//  Board  //
		board=new JPanel();
		board.setLayout(new GridLayout(7, 6,0,0));
		board.setPreferredSize(new Dimension(900,mainWindow.getHeight()-112));
		board.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0),4,false));
		cells=new JButton[7][6];
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<6;j++)
			{
				cells[i][j]=new JButton();
				cells[i][j].addActionListener(this);
				cells[i][j].addMouseListener(new mouseHover());
				cells[i][j].setActionCommand("Piece");
				cells[i][j].setName(game.getCellAt(i, j).toString());
				cells[i][j].setBorderPainted(false);
				board.add(cells[i][j]);
			}
		}
		updateCells();
		board_control= new JPanel();
		board_control.setLayout(new BorderLayout());
		board_control.add(board,BorderLayout.NORTH);
		board_control.add(cpanel,BorderLayout.SOUTH);
		mainWindow.add(board_control,BorderLayout.CENTER);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
		mainWindow.pack();
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("backgroundmusic.wav"));
	        clipBackMusic = AudioSystem.getClip();
	        clipBackMusic.open(audioInputStream);
	        clipBackMusic.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound, we removed this audio file since it's too big for evaluator.in we will include the file on a flash drive on evaluation day :)");
	    }	
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Piece"))
		{	
			JButton o= (JButton)e.getSource();
			Rectangle r=o.getBounds();
			Point point=o.getLocation();
			int i= point.y / r.height;
			int j=point.x / r.width;
			tempPiece=game.getCellAt(i, j).getPiece();
			if(tempPiece==null && heroP!=null && powerOnPieceP!=null && selectedPieceP==null)
			{
				try {
			        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("select.wav"));
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
				newPos=new Point(i,j);
				if(pieceNullB!=null)
					pieceNullB.setEnabled(true);
				pieceNullB=(JButton)e.getSource();
				pieceNullB.setEnabled(false);
				if(directionB!=null)
				{
					directionB.setEnabled(true);
					directionB=null;
					direction=null;
				}
				return;
			}
			else if((tempPiece instanceof Tech && heroP==null && powerOnPieceP==null)||(heroP!=null && powerOnPieceP!=null && tempPiece instanceof Tech))
			{
				try {
			        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("select.wav"));
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
				heroP=tempPiece;
				if(heroB!=null)
					heroB.setEnabled(true);
				heroB=(JButton)e.getSource();
				heroB.setEnabled(false);
				
				if(selectedPieceB!=null) {
					selectedPieceB.setEnabled(true);
					selectedPieceB=null;
					selectedPieceP=null;
				}	
				if(directionB!=null)
				{
					directionB.setEnabled(true);
					directionB=null;
					direction=null;
				}
				if(pieceNullB!=null) {
					pieceNullB.setEnabled(true);
					pieceNullB=null;
					newPos=null;
				}
				if(powerOnPieceB!=null) {
					powerOnPieceB.setEnabled(true);
					powerOnPieceB=null;
					powerOnPieceP=null;
				}
				return;
			}
			else if(tempPiece!=null && heroP!=null && powerOnPieceP==null && selectedPieceP==null)
			{
				try {
			        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("select.wav"));
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
				if(powerOnPieceB!=null)
					powerOnPieceB.setEnabled(true);
				powerOnPieceP=tempPiece;
				powerOnPieceB=(JButton)e.getSource();
				powerOnPieceB.setEnabled(false);
				
				if(selectedPieceB!=null) {
					selectedPieceB.setEnabled(true);
					selectedPieceB=null;
					selectedPieceP=null;
				}
				if(directionB!=null)
				{
					directionB.setEnabled(true);
					directionB=null;
					direction=null;
				}
				if(pieceNullB!=null) {
					pieceNullB.setEnabled(true);
					pieceNullB=null;
					newPos=null;
				}
				
				return;
			}
			else if(tempPiece!=null && ((heroP!=null && powerOnPieceP!=null) || (heroP==null && powerOnPieceP==null && !(tempPiece instanceof Tech)))) {
				try {
			        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("select.wav"));
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
				
				selectedPieceP=tempPiece;
				if(selectedPieceB!=null)
					selectedPieceB.setEnabled(true);
				selectedPieceB=(JButton)e.getSource();
				selectedPieceB.setEnabled(false);
				if(heroB!=null) {
					heroB.setEnabled(true);
					heroB=null;
					heroP=null;
				}
				if(powerOnPieceB!=null) {
					powerOnPieceB.setEnabled(true);
					powerOnPieceB=null;
					powerOnPieceP=null;
				}
				if(pieceNullB!=null) {
					pieceNullB.setEnabled(true);
					pieceNullB=null;
				}
				newPos=null;
				return;
			}
			else 
				return;
			
		}
		else if(e.getActionCommand().equals("Move")) {
			if((heroP!=null && powerOnPieceP!=null) || direction==null || (selectedPieceP==null && heroP==null))
				return;
			
			try {
				if(heroP!=null)
					heroP.move(direction);
				else {
					selectedPieceP.move(direction);
					if(selectedPieceP instanceof Armored) {
						try {
					        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("mortada.wav"));
					        clip = AudioSystem.getClip();
					        clip.open(audioInputStream);
					        clip.start();
					    } catch(Exception ex) {
					        System.out.println("Error with playing sound.");
					    }
					}
					if(selectedPieceP instanceof Speedster) {
						try {
					        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("bigshaq.wav"));
					        clip = AudioSystem.getClip();
					        clip.open(audioInputStream);
					        clip.start();
					    } catch(Exception ex) {
					        System.out.println("Error with playing sound.");
					    }
					}
					if(selectedPieceP instanceof Medic) {
						try {
					        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("magdy.wav"));
					        clip = AudioSystem.getClip();
					        clip.open(audioInputStream);
					        clip.start();
					    } catch(Exception ex) {
					        System.out.println("Error with playing sound.");
					    }
					}
				}
				try {
			        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("blop.wav"));
			        clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
			}
			catch(Exception moveE) {
				JOptionPane.showMessageDialog(null, moveE.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			finally {
				updateBoard();
				updateCells();
				mainWindow.revalidate();
				mainWindow.repaint();
				if(pieceNullB!=null) {
					pieceNullB.setEnabled(true);
					pieceNullB=null;
				}
				if(heroB!=null) {
					heroB.setEnabled(true);
					heroB=null;
				}
				if(powerOnPieceB!=null) {
					powerOnPieceB.setEnabled(true);
					powerOnPieceB=null;
				}
				if(selectedPieceB!=null) {
					selectedPieceB.setEnabled(true);
					selectedPieceB=null;
				}
				if(directionB!=null) {
					directionB.setEnabled(true);
					directionB=null;
				}
				heroP=null;
				selectedPieceP=null;
				powerOnPieceP=null;
				direction=null;
			}	
		}
		
		else if(e.getActionCommand().equals("Use Power")) {
			if((heroP==null && powerOnPieceP==null && selectedPieceP==null) || (heroP!=null && powerOnPieceP==null) || (selectedPieceP!=null && direction==null) || (selectedPieceP instanceof NonActivatablePowerHero) || (selectedPieceP instanceof SideKick) || (selectedPieceP instanceof Medic && medicTargetPiece==null))
				return;
			try {
				if(heroP!=null) {
					((ActivatablePowerHero)heroP).usePower(direction, powerOnPieceP, newPos);
					try {
				        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("powersound.wav"));
				        clip = AudioSystem.getClip();
				        clip.open(audioInputStream);
				        clip.start();
				    } catch(Exception ex) {
				        System.out.println("Error with playing sound.");
				    }
				}
				else {
					((ActivatablePowerHero)selectedPieceP).usePower(direction, medicTargetPiece, newPos);
					try {
				        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("powersound.wav"));
				        clip = AudioSystem.getClip();
				        clip.open(audioInputStream);
				        clip.start();
				    } catch(Exception ex) {
				        System.out.println("Error with playing sound.");
				    }
				}
			}
			catch(Exception usePowerE){
				JOptionPane.showMessageDialog(null, usePowerE.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			finally 
			{
				updateBoard();
				updateCells();
				mainWindow.revalidate();
				mainWindow.repaint();
				if(pieceNullB!=null) {
					pieceNullB.setEnabled(true);
					pieceNullB=null;
				}
				if(heroB!=null) {
					heroB.setEnabled(true);
					heroB=null;
				}
				if(powerOnPieceB!=null) {
					powerOnPieceB.setEnabled(true);
					powerOnPieceB=null;
				}
				if(selectedPieceB!=null) {
					selectedPieceB.setEnabled(true);
					selectedPieceB=null;
				}
				if(directionB!=null) {
					directionB.setEnabled(true);
					directionB=null;
				}
				heroP=null;
				selectedPieceP=null;
				powerOnPieceP=null;
				direction=null;
			}
		}
		else if(e.getActionCommand().equals("comboBoxChanged")) {
			if(medicTargetPiece!=null) {
				if(d1!=null) {
					d1=null;
					if((Piece)deadP2.getSelectedItem()!=null)
						deadP1.setSelectedItem(null);
						
				}
				else {
					if((Piece)deadP1.getSelectedItem()!=null)
						deadP2.setSelectedItem(null);
					d2=null;
				}
				medicTargetPiece=null;
			}
			d1=(Piece)deadP1.getSelectedItem();
			d2=(Piece)deadP2.getSelectedItem();
			if(d1==null)
				medicTargetPiece=d2;
			else
				medicTargetPiece=d1;	
		}
		else if (e.getActionCommand().equals("Quit")) {
			System.exit(0);
		}
		else
		{
			switch(e.getActionCommand())
			{
				case "UL":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.UPLEFT;break;
				case "UR":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.UPRIGHT;break;
				case "U":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.UP;break;
				case "L":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.LEFT;break;
				case "R":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.RIGHT;break;
				case "DL":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.DOWNLEFT;break;
				case "D":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.DOWN;break;
				case "DR":if(directionB!=null)directionB.setEnabled(true);directionB=(JButton)e.getSource();directionB.setEnabled(false);direction=Direction.DOWNRIGHT;break;
				default:break;
			}
		}
	}
	public void updateBoard() {
		//  update board pieces  //
		board.removeAll();  
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(!(cells[i][j].getName().equals(game.getCellAt(i, j).toString()))) {
					cells[i][j].setName(game.getCellAt(i, j).toString());
					board.add(cells[i][j]);
					
				}
				else
					board.add(cells[i][j]);		
			}
		}
		//  update current player name  //
		currentLabel.setText(game.getCurrentPlayer().getName());
		if(game.getCurrentPlayer().getName().equals(game.getPlayer1().getName()))
			currentLabel.setForeground(Color.RED);
		else
			currentLabel.setForeground(Color.GREEN);
		
		 //  update player1 score  //
		if(game.getPlayer1().getPayloadPos()>=1) {
			int p1score=6-game.getPlayer1().getPayloadPos();
			for(int i = p1score;i<6;i++) {
				colorLabelp1[i].setIcon(new ImageIcon(getClass().getResource("p1smoke.png")));
			}
		}
		
		//  update player2 score  //
		if(game.getPlayer2().getPayloadPos()>=1) {
			int p2score=6-game.getPlayer2().getPayloadPos();
			for(int i = p2score;i<6;i++) {
				colorLabelp2[i].setIcon(new ImageIcon(getClass().getResource("p2smoke.png")));
			}
		}
		
		// Check Winner & end game if someone won  //
		if(game.getPlayer1().getPayloadPos()==game.getPayloadPosTarget()) 
		{
			clipBackMusic.stop();
			try {
		        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("winning.wav"));
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		    }
			JOptionPane.showMessageDialog(null,game.getPlayer1().getName()+" Wins","Congratulations!",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else if(game.getPlayer2().getPayloadPos()==game.getPayloadPosTarget())
		{
			try {
		        audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("winning.wav"));
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		    }
			JOptionPane.showMessageDialog(null,game.getPlayer2().getName()+" Wins","Congratulations!",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		//  Update DeadCharacters of player 1 & 2  //
		deadP1.removeAllItems();
		for(int i =0;i<game.getPlayer1().getDeadCharacters().size();i++) {
			deadP1.addItem(game.getPlayer1().getDeadCharacters().get(i));
		}
		deadP1.setSelectedItem(null);
		
		deadP2.removeAllItems();
		for(int i =0;i<game.getPlayer2().getDeadCharacters().size();i++) {
			deadP2.addItem(game.getPlayer2().getDeadCharacters().get(i));
		}
		deadP2.setSelectedItem(null);
	}
	public void updateCells() {
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<6;j++)
			{
				Piece tmp=game.getCellAt(i, j).getPiece();
				ImageIcon imagetmp;
				if(tmp instanceof Armored)
				{
					if(((Armored) tmp).isArmorUp()) {
						if(tmp.getOwner()==game.getPlayer1())
							imagetmp=new ImageIcon(getClass().getResource("ARMOR1.png"));
						else
							imagetmp=new ImageIcon(getClass().getResource("ARMOR2.png"));
						}
					else {
						if(tmp.getOwner()==game.getPlayer1())
							imagetmp=new ImageIcon(getClass().getResource("ARMORDOWN1.png"));
						else
							imagetmp=new ImageIcon(getClass().getResource("ARMORDOWN2.png"));
					}
						
				}
				else if(tmp instanceof Ranged)
				{	
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("RANGED1.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("RANGED2.png"));
				}
				else if(tmp instanceof Medic)
				{	
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("MEDIC1n.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("MEDIC2n.png"));
				}
				else if(tmp instanceof Speedster)
				{
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("SPEEDSTER1.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("SPEEDSTER2.png"));
				}
				else if(tmp instanceof Super)
				{
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("SUPER1.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("SUPER2.png"));
				}
				else if(tmp instanceof Tech)
				{
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("TECH1.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("TECH2.png"));
				}
				else if(tmp instanceof SideKick)
				{
					if(tmp.getOwner()==game.getPlayer1())
						imagetmp=new ImageIcon(getClass().getResource("SK1.png"));
					else
						imagetmp=new ImageIcon(getClass().getResource("SK2.png"));
				}
				else
					imagetmp=new ImageIcon(getClass().getResource("null.png"));
				
				cells[i][j].setIcon(imagetmp);
			}
		}
	}
	public static void main(String[]args)
	{
		new GameView();
	}
	
	public class mouseHover extends MouseAdapter{
		private JWindow tooltip;
		private JLabel label;
		public void mouseEntered(MouseEvent e) {
			JButton o= (JButton)e.getSource();
			Rectangle r=o.getBounds();
			Point point=o.getLocation();
			int i= point.y / r.height;
			int j=point.x / r.width;
			Piece hoveredOn=game.getCellAt(i, j).getPiece();
			if(hoveredOn==null)
				return;
			String type;
			String power;
			if(hoveredOn instanceof Armored)
				type="Armored";
			else if(hoveredOn instanceof Medic)
				type="Medic";
			else if(hoveredOn instanceof Ranged)
				type="Ranged";
			else if(hoveredOn instanceof Speedster)
				type="Speedster";
			else if(hoveredOn instanceof Super)
				type="Super";
			else if(hoveredOn instanceof Tech)
				type="Tech";
			else
				type="SideKick";
			if(hoveredOn instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero)hoveredOn).isPowerUsed()==true)
					power="Not Available";
				else
					power="Available";
			}
			else if(hoveredOn instanceof Armored)
			{
				if(((Armored)hoveredOn).isArmorUp()==true)
					power="Up";
				else
					power="Down";
			}
			else
				power="Passive";
			
			if (tooltip==null) {
                tooltip = new JWindow();
                tooltip.setOpacity((float)0.75);
                label = new JLabel();
                label.setBackground(Color.BLACK);
                label.setOpaque(true);
                tooltip.add(label);
			}
			
			if(hoveredOn instanceof Hero && power.equals("Available"))
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>Power Use</p><p>&nbsp; &nbsp; <strong><span style='color: #00ff00;'>"+power+"</span></strong></p><p>&nbsp;</p>"+"</html>");
			else if(hoveredOn instanceof Hero && power.equals("Not Available"))
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>Power Use</p><p>&nbsp; &nbsp; <strong><span style='color: #ff0000;'>"+power+"</span></strong></p><p>&nbsp;</p>"+"</html>");
			else if(hoveredOn instanceof Armored && power.equals("Up"))
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>Armor Status</p><p>&nbsp; &nbsp; <strong><span style='color: #00ff00;'>"+power+"</span></strong></p><p>&nbsp;</p>"+"</html>");
			else if(hoveredOn instanceof Armored && power.equals("Down"))
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>Armor Status</p><p>&nbsp; &nbsp; <strong><span style='color: #ff0000;'>"+power+"</span></strong></p><p>&nbsp;</p>"+"</html>");
			else if(hoveredOn instanceof NonActivatablePowerHero)
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>Power Use</p><p>&nbsp; &nbsp; <strong><span style='color: #ffff00;'>"+power+"</span></strong></p><p>&nbsp;</p>"+"</html>");
			else if(hoveredOn instanceof SideKick)
				label.setText("<html>"+"<p>Name</p><p>&nbsp; &nbsp; <strong><span style='text-decoration: underline;'><em>"+game.getCellAt(i,j).toString()+"</em></span></strong></p><p>Type</p><p>&nbsp; &nbsp; <strong><span style='color: #ff6600;'>"+type+"</span></strong></p><p>&nbsp;</p>"+"</html");
			else
				label.setText("Empty Cell");
			label.setFont(new Font("Arial",Font.PLAIN,13));
			label.setForeground(Color.WHITE);
			Component c = (Component)e.getSource();
            tooltip.setBounds(c.getLocationOnScreen().x, c.getLocationOnScreen().y+c.getHeight(), c.getWidth(), c.getHeight());
            label.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			tooltip.setVisible(true);
		}
		public void mouseExited(MouseEvent e) {
			if(tooltip!=null) {
				tooltip.setVisible(false);
				tooltip.dispose();
			}
		}
	}
	public class mouseClickOnPanel extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			initialClick = e.getPoint();
            getComponentAt(initialClick);
		}
	}
	public class mouseDrag extends MouseAdapter{
		public void mouseDragged(MouseEvent e) {
            int thisX = mainWindow.getLocation().x;
            int thisY = mainWindow.getLocation().y;
            int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
            int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            mainWindow.setLocation(X, Y);
            mainWindow.repaint();
            mainWindow.revalidate();
            
		}
	}
}
