package snakecopy2;
import snakecopy2.AI;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JFrame;

public class GameCopy extends JFrame{

	public static AI individual;
	public static LinkedList<AI> individuals = new LinkedList<AI>();
	public static Board myBoard;
	public static int[] scores;
	public static int moves;
    public static int maxMoves;
    public static int xMax;
    public static int xCurrent;
    public static AI newIndiv;
    public static ArrayList<Double> generationScores;
    public static int patternIndex;
    public static int patternX[] = {9, 6, 8, 0, 6, 5, 7, 7, 1, 4, 4, 2, 4, 5, 8, 3, 1, 2, 2, 3, 6, 3, 5, 1, 4, 7, 7, 5, 7, 9, 6, 7, 1, 2, 2, 8, 5, 0, 3, 2, 9, 8, 9, 8, 6, 6, 8, 0, 1, 9, 1, 7, 4, 7, 8, 6, 2, 9, 9, 2, 3, 1, 3, 1, 0, 9, 4, 8, 2, 2, 1, 6, 8, 4, 8, 3, 0, 1, 8, 1, 4, 2, 1, 6, 3, 3, 6, 0, 3, 1, 2, 9, 8, 5, 9, 1, 7, 4, 7, 0, 1, 3, 9, 4, 0, 1, 9, 1, 2, 0, 8, 3, 2, 2, 6, 6, 5, 5, 5, 0, 9, 6, 8, 7, 7, 4, 4, 7, 4, 7, 6, 3, 3, 0, 6, 7, 0, 7, 9, 8, 7, 1, 2, 5, 2, 0, 2, 8, 7, 3, 7, 3, 0, 5, 4, 4, 4, 4, 9, 8, 1, 7, 0, 7, 8, 2, 8, 0, 9, 2, 6, 1, 5, 5, 8, 6, 1, 9, 6, 8, 1, 9, 3, 6, 2, 0, 6, 7, 4, 9, 9, 2, 1, 6, 4, 4, 2, 7, 0, 7, 0, 7, 6, 4, 5, 3, 0, 3, 4, 6, 7, 1, 5, 2, 5, 8, 8, 7, 7, 1, 9, 5, 0, 2, 7, 8, 3, 6, 2, 6, 4, 3, 1, 5, 0, 1, 3, 4, 6, 4, 1, 7, 5, 3, 7, 8, 5, 8, 8, 3, 1, 9, 6, 8, 1, 2, 2, 9, 2, 4, 3, 7, 5, 3, 6, 3, 6, 2, 2, 1, 9, 9, 5, 1, 3, 9, 6, 7, 1, 6, 9, 6, 6, 4, 8, 6, 4, 7, 0, 5, 8, 4, 1, 3, 2, 7, 2, 9, 5, 9, 5, 7, 9, 6, 4, 0, 8, 2, 3, 6, 9, 6, 6, 0, 5, 7, 1, 8, 5, 6, 9, 9, 4, 2, 9, 7, 9, 2, 3, 2, 9, 2, 3, 6, 2, 5, 9, 0, 7, 0, 3, 9, 9, 1, 2, 8, 4, 9, 1, 3, 3, 3, 6, 4, 7, 6, 4, 4, 6, 3, 9, 8, 5, 1, 4, 6, 0, 0, 8, 7, 4, 8, 2, 8, 3, 8, 9, 0, 5, 2, 9, 5, 3, 8, 2, 8, 4, 6, 8, 9, 8, 2, 3, 4, 6, 5, 8, 5, 1, 4, 8, 4, 4, 1, 0, 0, 8, 8, 8, 7, 3, 8, 3, 0, 0, 3, 8, 0, 3, 8, 9, 6, 5, 8, 8, 0, 5, 6, 2, 4, 2, 0, 0, 6, 6, 9, 9, 2, 1, 2, 6, 4, 1, 7, 7, 4, 9, 1, 2, 7, 4, 6, 2, 2, 3, 8, 2, 4, 2, 3, 7, 8, 5, 1, 1, 2, 5, 2, 8, 7, 7, 7, 3, 2, 4, 3, 4, 9, 7, 4, 2, 7, 0, 2, 6, 7, 5, 0, 0, 3, 5, 0, 7, 5, 1, 5, 2, 7, 9, 8, 5, 8, 1, 1, 7, 6, 8, 9, 4, 8, 6, 8, 0, 5, 3, 0, 0, 6, 0, 9, 5, 3, 0, 8, 4, 5, 0, 4, 4, 9, 1, 4, 6, 4, 4, 7, 6, 4, 9, 0, 6, 1, 5, 8, 3, 9, 1, 6, 7, 5, 5, 7, 6, 4, 3, 4, 5, 3, 7, 0, 9, 2, 0, 6, 1, 6, 2, 4, 8, 2, 5, 4, 6, 8, 9, 1, 0, 2, 2, 3, 9, 8, 3, 9, 1, 9, 8, 2, 3, 9, 8, 9, 1, 6, 0, 1, 3, 2, 6, 3, 2, 9, 6, 0, 0, 6, 7, 8, 5, 6, 0, 9, 2, 8, 5, 9, 7, 9, 0, 5, 7, 4, 8, 7, 6, 5, 1, 3, 2, 1, 0, 1, 7, 1, 4, 9, 1, 6, 7, 9, 9, 5, 9, 4, 7, 8, 0, 8, 7, 2, 3, 0, 2, 9, 1, 5, 4, 1, 7, 9, 4, 1, 6, 0, 5, 0, 5, 5, 5, 2, 2, 1, 4, 1, 1, 5, 4, 2, 6, 0, 6, 8, 9, 4, 3, 5, 8, 4, 5, 1, 4, 3, 9, 9, 1, 7, 8, 6, 4, 5, 4, 6, 6, 2, 1, 2, 9, 6, 1, 3, 7, 0, 6, 9, 5, 4, 8, 1, 5, 2, 3, 9, 8, 4, 4, 5, 8, 6, 0, 2, 3, 9, 2, 5, 4, 1, 5, 3, 0, 5, 6, 5, 0, 1, 9, 9, 5, 1, 7, 2, 3, 5, 5, 6, 6, 8, 2, 8, 6, 6, 6, 1, 5, 3, 2, 9, 5, 5, 7, 6, 9, 7, 3, 2, 5, 2, 7, 2, 5, 9, 5, 7, 6, 0, 3, 0, 2, 5, 4, 8, 3, 2, 7, 2, 9, 3, 0, 8, 2, 2, 4, 1, 2, 2, 0, 1, 8, 8, 2, 9, 3, 8, 6, 3, 9, 1, 2, 8, 7, 6, 7, 8, 1, 7, 3, 3, 4, 2, 8, 6, 9, 6, 0, 5, 3, 8, 0, 0, 1, 1, 5, 9, 9, 0, 2, 4, 7, 3, 0, 2, 0, 3, 6, 0, 2, 0, 9, 0, 7, 7, 6, 5, 6, 3, 6, 4, 2, 2, 2, 8, 6, 1, 1, 8, 2, 5, 5, 8, 6, 8, 2, 6, 0, 2, 2, 1, 3, 9, 2, 4, 9, 9, 0, 4, 7, 8, 9, 1, 3, 6, 7, 6, 7, 3, 3, 1, 7, 3, 6, 5, 6, 2, 6, 9, 0, 1, 8, 8, 2, 7, 5, 9, 4, 5, 0, 7, 9, 7, 6, 8, 7, 9, 5, 0, 8, 5, 6, 3, 9, 5, 6, 7, 6, 9, 5, 1, 8, 9, 0, 1, 4, 7, 2, 6, 4, 9, 7, 0, 3, 5, 9, 8, 2, 1, 1, 5, 3, 5, 7, 7, 9, 3, 3, 9, 1, 4, 0, 0, 7, 2, 7, 5, 3, 9, 5, 4, 5, 7, 8, 4, 9, 0, 0, 0, 3, 8, 6, 2, 0, 9, 7, 5, 8, 9, 9, 0, 4, 2, 4, 5, 1, 6, 4, 6, 9, 4, 5, 4, 7, 4, 8, 9, 7, 9, 9, 6, 1, 7, 8, 5, 4, 3, 4, 0, 1, 7, 3, 0, 6, 3, 0, 4, 4, 3, 8, 4, 8, 8, 5, 6, 2, 2, 8, 4, 7, 3, 3, 5, 7, 1, 2, 0, 8, 9, 5, 3, 9, 2, 7, 8, 6, 4, 3, 3, 0, 9, 4, 3, 8, 0, 7, 8, 2, 7, 1, 5, 2, 1, 3, 8, 6, 5, 8, 9, 2, 3, 4, 8, 5, 2, 4, 1, 1, 6, 0, 2, 3, 2, 6, 3, 2, 7, 2, 2, 9, 4, 6, 2, 2, 9, 3, 6, 5, 2, 8, 7, 2, 5, 2, 6, 7, 6, 7, 5, 0, 4, 0, 9, 8, 9, 9, 5, 2, 4, 6, 8, 2, 6, 2, 9, 5, 0, 1, 1, 8, 5, 7, 5, 3, 1, 3, 4, 0, 6, 1, 7, 7, 2, 3, 5, 2, 9, 1, 7, 9, 9, 2, 9, 8, 1, 7, 3, 5, 5, 3, 6, 7, 0, 3, 8, 9, 2, 4, 5, 9, 7, 5, 3, 8, 0, 4, 1, 2, 7, 6, 2, 6, 0, 7, 9, 0, 8, 1, 2, 1, 4, 8, 7, 3, 9, 7, 6, 1, 3, 5, 7, 1, 8, 6, 0, 8, 0, 8, 0, 8, 5, 3, 9, 9, 4, 9, 2, 1, 2, 8, 1, 4, 3, 1, 8, 9, 8, 5, 6, 9, 8, 5, 8, 2, 8, 0, 7, 3, 6, 8, 6, 2, 9, 0, 1, 1, 0, 8, 6, 0, 8, 0, 0, 8, 9, 3, 0, 4, 5, 6, 9, 5, 6, 1, 1, 0, 5, 9, 0, 8, 9, 4, 8, 7, 3, 1, 9, 2, 0, 5, 2, 7, 0, 3, 7, 8, 7, 3, 3, 1, 6, 4, 4, 5, 0, 2, 8, 5, 7, 4, 1, 1, 2, 7, 6, 1, 7, 5, 9, 0, 5, 8, 1, 0, 1, 9, 3, 7, 2, 2, 1, 9, 2, 7, 5, 0, 4, 6, 5, 6, 8, 5, 1, 7, 3, 6, 1, 2, 9, 0, 4, 7, 0, 3, 8, 6, 2, 6, 1, 7, 6, 3, 1, 1, 5, 9, 4, 7, 7, 3, 3, 8, 6, 0, 2, 9, 4, 9, 9, 4, 2, 3, 4, 7, 2, 3, 3, 3, 6, 0, 4, 7, 5, 4, 2, 9, 8, 9, 1, 7, 5, 3, 4, 5, 6, 8, 2, 4, 3, 5, 2, 3, 9, 6, 7, 0, 0, 3, 9, 7, 6, 2, 0, 8, 3, 0, 0, 8, 1, 1, 0, 9, 3, 8, 5, 6, 6, 4, 7, 8, 3, 4, 1, 0, 9, 2, 8, 5, 7, 3, 0, 6, 4, 0, 9, 0, 3, 4, 1, 3, 2, 7, 7, 5, 3, 1, 7, 9, 0, 6, 9, 3, 3, 4, 5, 8, 2, 0, 1, 7, 7, 5, 8, 8, 5, 2, 2, 1, 2, 8, 8, 3, 5, 2, 3, 5, 7, 7, 6, 0, 9, 1, 3, 9, 8, 5, 5, 9, 4, 2, 6, 5, 0, 8, 8, 1, 2, 1, 6, 2, 6, 2, 9, 5, 8, 1, 0, 5, 5, 5, 0, 6, 1, 8, 5, 6, 0, 9, 8, 5, 9, 5, 3, 2, 0, 6, 0, 5, 4, 0, 1, 4, 3, 5, 4, 4, 2, 3, 5, 0, 5, 3, 5, 9, 7, 2, 8, 9, 5, 7, 6, 7, 4, 8, 1, 5, 3, 7, 8, 2, 6, 9, 0, 6, 4, 4, 0, 4, 3, 7, 9, 9, 6, 6, 2, 2, 0, 6, 7, 4, 8, 6, 6, 8, 3, 8, 8, 7, 2, 5, 4, 5, 0, 8, 1, 4, 1, 7, 1, 4, 2, 7, 7, 0, 5, 9, 6, 8, 5, 8, 2, 3, 2, 9, 2, 0, 9, 6, 2, 5, 2, 0, 3, 9, 9, 5, 5, 9, 7, 0, 5, 4, 4, 7, 9, 0, 1, 2, 9, 9, 9, 2, 9, 2, 6, 8, 3, 9, 7, 8, 0, 3, 6, 7, 8, 3, 7, 8, 2, 9, 0, 0, 7, 2, 9, 7, 1, 3, 2, 9, 9, 7, 5, 1, 6, 2, 7, 2, 3, 8, 1, 6, 9, 9, 2, 0, 9, 7, 3, 4, 3, 1, 1, 5, 7, 5, 3, 9, 0, 1, 7, 8, 2, 6, 3, 3, 9, 5, 2, 6, 4, 8, 7, 0, 8, 0, 2, 7, 0, 4, 8, 1, 6, 7, 3, 2, 3, 0, 9, 9, 5, 6, 6, 0, 7, 7, 2, 6, 0, 5, 4, 7, 7, 5, 1, 3, 4, 5, 4, 6, 4, 1, 7, 5, 4, 0, 3, 9, 2, 3, 5, 6, 6, 9, 9, 4, 6, 7, 2, 4, 5, 5, 6, 8, 7, 3, 1, 8, 8, 5, 6, 9, 2, 0, 0, 6, 9, 6, 3, 8, 5, 9, 6, 6, 1, 9, 9, 0, 3, 1, 0, 1, 7, 8, 1, 3, 7, 6, 7, 2, 0, 4, 9, 9, 1, 5, 9, 3, 7, 5, 8, 9, 2, 7, 4, 7, 5, 7, 1, 7, 2, 6, 2, 7, 6, 1, 0, 1, 1, 1, 0, 3, 5, 6, 3, 5, 6, 6, 4, 5, 0, 1, 8, 6, 2, 2, 7, 4, 9, 2, 2, 7, 7, 1, 6, 3, 9, 7, 4, 5, 7, 9, 3, 3, 5, 1, 3, 5, 2, 5, 5, 5, 0, 2, 9, 4, 2, 4, 2, 7, 0, 5, 0, 0, 8, 0, 7, 0, 9, 7, 5, 7, 4, 5, 3, 6, 9, 7, 5, 6, 2, 2, 3, 6, 4, 1, 0, 3, 7, 1, 2, 0, 7, 7, 8, 1, 4, 5, 3, 5, 0, 9, 5, 7, 0, 7, 9, 0, 6, 2, 1, 4, 6, 0, 0, 5, 7, 3, 8, 1, 2, 5, 0, 6, 1, 8, 1, 3, 5, 5, 2, 9, 9, 8, 5, 7, 1, 8, 5, 6, 9, 3, 1, 3, 6, 9, 5, 9, 3, 1, 5, 5, 9, 9, 6, 1, 5, 4, 6, 4, 7, 9, 1, 9, 4, 6, 0, 1, 4, 4, 5, 9, 3, 1, 4, 0, 2, 5, 4, 0, 3, 1, 7, 0, 4, 8, 0, 0, 8, 9, 8, 1, 9, 3, 2, 6, 3, 3, 5, 1, 3, 9, 0, 8, 9, 4, 8, 6, 3, 9, 9, 2, 9, 8, 0, 0, 8, 9, 9, 4, 1, 9, 1, 8, 8, 3, 4, 0, 8, 0, 5, 3, 5, 8, 4, 9, 0, 2, 1, 8, 1, 8, 8, 2, 9, 8, 5, 7, 6, 4, 2, 1, 0, 4, 7, 7, 3, 3, 3, 2, 6, 2, 0, 4, 6, 5, 5, 9, 3, 2, 9, 1, 6, 9, 7, 2, 3, 8, 9, 4, 1, 5, 5, 8, 3, 5, 1, 8, 1, 7, 3, 4, 8, 6, 9, 3, 4, 8, 8, 3, 1, 4, 9, 1, 0, 3, 0, 7, 8, 8, 9, 4, 8, 1, 6, 7, 7, 7, 3, 0, 4, 9, 2, 1, 8, 8, 7, 0, 5, 3, 0, 0, 0, 5, 8, 5, 5, 0, 6, 7, 3, 8, 9, 7, 4, 1, 2, 2, 0, 8, 9, 9, 0, 4, 2, 1, 0, 2, 9, 1, 0, 9, 5, 7, 2, 9, 2, 3, 6, 6, 5, 6, 7, 1, 8, 6, 7, 2, 1, 2, 7, 8, 1, 3, 4, 4, 6, 9, 7, 4, 5, 1, 0, 5, 3, 3, 6, 3, 1, 5, 8, 7, 4, 4, 7, 2, 8, 3, 5, 2, 2, 6, 8, 4, 5, 8, 9, 6, 3, 0, 3, 8, 7, 2, 7, 5, 0, 9, 3, 3, 2, 3, 9, 7, 9, 4, 4, 1, 6, 4, 1, 6, 5, 3, 0, 3, 5, 0, 3, 6, 3, 5, 4, 0, 2, 6, 7, 3, 8, 8, 5, 0, 7, 3, 0, 9, 4, 8, 9, 7, 1, 2, 8, 2, 2, 1, 5, 0, 2, 3, 3, 2, 2, 5, 6, 6, 1, 2, 7, 7, 6, 3, 5, 4, 7, 4, 4, 3, 3, 1, 4, 7, 9, 4, 3, 6, 6, 6, 2, 2, 2, 6, 2, 1, 1, 7, 8, 2, 0, 1, 9, 6, 9, 3, 7, 2, 8, 5, 6, 1, 0, 6, 3, 8, 6, 0, 6, 7, 3, 7, 8, 5, 8, 4, 4, 6, 3, 0, 9, 9, 8, 0, 7, 6, 1, 4, 0, 7, 4, 1, 6, 2, 8, 2, 3, 3, 8, 9, 1, 1, 2, 1, 4, 5, 4, 7, 0, 0, 9, 9, 2, 6, 0, 0, 7, 2, 2, 2, 2, 1, 7, 1, 6, 7, 0, 3, 2, 9, 2, 5, 3, 1, 2, 7, 2, 8, 8, 2, 9, 9, 3, 1, 2, 5, 7, 8, 2, 7, 0, 1, 5, 7, 7, 6, 4, 7, 1, 3, 1, 8, 6, 4, 5, 7, 7, 4, 9, 4, 8, 2, 0, 1, 6, 3, 6, 1, 4, 5, 7, 5, 3, 1, 3, 1, 1, 2, 9, 3, 6, 3, 5, 5, 6, 5, 1, 3, 6, 4, 3, 1, 9, 8, 3, 0, 5, 8, 7, 2, 6, 0, 1, 8, 6, 5, 6, 3, 6, 2, 9, 7, 0, 0, 2, 5, 2, 1, 2, 3, 9, 8, 8, 0, 7, 7, 3, 1, 2, 5, 7, 8, 8, 6, 0, 9, 3, 2, 3, 0, 8, 9, 3, 2, 1, 1, 2, 2, 5, 8, 1, 7, 0, 1, 8, 3, 5, 6, 0, 3, 8, 1, 6, 2, 8, 4, 4, 9, 8, 2, 3, 0, 1, 7, 7, 3, 8, 9, 1, 2, 7, 7, 9, 4, 1, 9, 5, 8, 6, 7, 0, 0, 9, 3, 5, 1, 6, 9, 8, 3, 3, 5, 2, 8, 9, 0, 1, 6, 3, 9, 9, 4, 1, 1, 1, 0, 4, 2, 1, 9, 4, 2, 6, 1, 7, 1, 3, 8, 3, 8, 2, 4, 1, 1, 7, 4, 7, 3, 3, 0, 8, 3, 0, 3, 7, 2, 0, 2, 8, 1, 8, 9, 0, 9, 3, 8, 6, 8, 9, 5, 5, 9, 4, 8, 6, 9, 5, 3, 8, 3, 6, 7, 2, 2, 2, 4, 1, 3, 2, 1, 8, 2, 7, 1, 9, 0, 6, 0, 3, 5, 1, 4, 7, 4, 7, 0, 9, 2, 3, 5, 9, 6, 9, 0, 2, 7, 1, 6, 9, 5, 3, 3, 3, 3, 0, 3, 4, 1, 5, 9, 5, 9, 2, 9, 2, 9, 2, 9, 6, 1, 7, 9, 2, 3, 8, 6, 1, 0, 6, 6, 4, 1, 1, 7, 0, 7, 1, 5, 9, 0, 8, 6, 9, 7, 7, 1, 1, 8, 4, 5, 2, 1, 6, 8, 6, 4, 6, 5, 6, 3, 2, 2, 8, 6, 7, 7, 0, 9, 1, 2, 0, 3, 7, 5, 9, 2, 2, 2, 6, 7, 0, 1, 2, 6, 8, 2, 7, 5, 4, 2, 7, 7, 1, 5, 3, 5, 6, 5, 2, 2, 5, 5, 6, 9, 6, 9, 9, 6, 6, 7, 5, 7, 9, 2, 5, 5, 4, 9, 7, 1, 9, 4, 8, 6, 2, 9, 7, 3, 0, 4, 0, 1, 1, 7, 5, 4, 3, 4, 1, 0, 5, 9, 7, 0, 3, 7, 0, 7, 6, 5, 6, 4, 9, 5, 9, 4, 0, 3, 4, 1, 1, 5, 8, 6, 6, 8, 8, 8, 7, 8, 8, 4, 4, 4, 1, 0, 8, 6, 1, 7, 7, 1, 9, 5, 8, 1, 4, 6, 6, 9, 0, 8, 8, 4, 5, 3, 9, 4, 1, 8, 4, 2, 3, 4, 2, 2, 6, 5, 2, 7, 2, 7, 5, 9, 1, 3, 8, 4, 0, 1, 0, 5, 9, 8, 1, 4, 9, 6, 4, 3, 9, 8, 8, 8, 9, 1, 9, 9, 1, 9, 1, 6, 1, 9, 8, 6, 3, 6, 2, 1, 4, 7, 0, 0, 8, 8, 7, 5, 6, 9, 6, 3, 4, 7, 5, 1, 9, 0, 0, 0, 6, 3, 7, 9, 9, 5, 8, 3, 8, 2, 1, 5, 5, 8, 0, 3, 1, 6, 2, 4, 9, 9, 3, 7, 7, 2, 7, 0, 0, 2, 0, 8, 2, 1, 6, 2, 2, 2, 0, 5, 2, 9, 6, 4, 1, 9, 4, 3, 9, 4, 2, 2, 2, 5, 8, 5, 6, 8, 7, 8, 5, 3, 2, 1, 0, 5, 2, 9, 6, 9, 4, 8, 5, 3, 6, 5, 3, 7, 5, 9, 9, 1, 4, 2, 6, 6, 7, 1, 3, 5, 9, 0, 3, 3, 6, 0, 5, 2, 7, 7, 6, 4, 1, 7, 3, 3, 4, 7, 3, 3, 2, 3, 3, 6, 5, 2, 2, 5, 8, 1, 3, 5, 6, 8, 4, 3, 5, 9, 3, 7, 7, 1, 8, 5, 9, 8, 3, 5, 1, 0, 6, 5, 8, 7, 3, 4, 1, 9, 3, 6, 6, 5, 6, 2, 3, 6, 9, 3, 8, 6, 1, 2, 6, 9, 5, 0, 9, 8, 1, 9, 8, 8, 7, 9, 2, 4, 7, 4, 1, 9, 7, 8, 9, 3, 8, 6, 4, 4, 3, 8, 5, 5, 0, 6, 1, 8, 3, 8, 6, 2, 4, 5, 9, 5, 7, 1, 4, 8, 0, 1, 3, 7, 6, 0, 2, 1, 9, 0, 6, 6, 2, 2, 6, 1, 4, 5, 8, 8, 5, 7, 3, 6, 0, 5, 8, 0, 8, 8, 3, 0, 6, 6, 9, 4, 3, 8, 3, 7, 5, 0, 6, 4, 7, 3, 2, 8, 9, 4, 8, 9, 9, 0, 7, 1, 5, 2, 1, 4, 6, 4, 1, 6, 8, 3, 0, 4, 9, 9, 8, 2, 8, 7, 5, 1, 2, 1, 3, 1, 6, 5, 9, 6, 1, 7, 9, 4, 9, 1, 8, 1, 9, 7, 0, 2, 6, 5, 2, 0, 7, 6, 5, 3, 8, 8, 5, 2, 4, 0, 2, 4, 8, 1, 7, 2, 9, 3, 8, 9, 3, 0, 9, 0, 2, 2, 1, 1, 9, 9, 1, 4, 5, 5, 3, 2, 8, 3, 0, 3, 4, 5, 1, 4, 4, 1, 7, 7, 3, 2, 9, 8, 8, 0, 7, 2, 1, 7, 9, 5, 8, 8, 7, 8, 8, 9, 4, 2, 0, 9, 8, 0, 9, 7, 7, 7, 7, 5, 7, 2, 5, 5, 4, 8, 8, 4, 9, 5, 1, 1, 0, 6, 1, 6, 3, 7, 5, 1, 8, 5, 4, 9, 9, 5, 0, 0, 5, 1, 3, 3, 0, 2, 9, 0, 2, 6, 0, 0, 4, 9, 4, 4, 1, 1, 6, 9, 5, 4, 9, 6, 4, 6, 6, 9, 7, 7, 2, 1, 4, 6, 5, 3, 1, 4, 2, 4, 8, 6, 3, 8, 6, 1, 7, 7, 3, 2, 2, 4, 3, 6, 3, 0, 5, 8, 9, 6, 0, 2, 6, 4, 8, 5, 1, 4, 0, 0, 3, 1, 6, 2, 9, 3, 1, 2, 5, 7, 0, 4, 9, 5, 2, 8, 6, 7, 3, 6, 8, 9, 9, 7, 0, 4, 9, 1, 5, 7, 0, 8, 7, 5, 8, 2, 3, 4, 3, 8, 3, 8, 7, 0, 7, 3, 8, 3, 9, 7, 1, 7, 2, 9, 2, 3, 5, 6, 6, 1, 6, 0, 8, 7, 7, 8, 3, 0, 4, 4, 5, 4, 8, 1, 3, 5, 8, 8, 6, 8, 5, 5, 7, 8, 0, 8, 8, 2, 2, 6, 0, 5, 0, 7, 9, 3, 7, 2, 0, 3, 5, 3, 1, 7, 0, 7, 2, 1, 3, 1, 2, 0, 4, 5, 8, 0, 3, 2, 4, 4, 5, 9, 6, 6, 0, 0, 9, 9, 9, 8, 6, 2, 4, 3, 4, 4, 8, 5, 1, 7, 9, 8, 0, 5, 8, 8, 1, 5, 7, 0, 1, 0, 7, 3, 3, 1, 4, 3, 1, 8, 4, 7, 8, 5, 1, 5, 8, 6, 5, 2, 1, 6, 1, 8, 8, 3, 6, 2, 3, 6, 0, 2, 6, 8, 9, 6, 5, 1, 0, 3, 6, 6, 3, 1, 1, 1, 3, 3, 8, 4, 5, 2, 8, 0, 9, 7, 1, 6, 9, 2, 9, 4, 0, 5, 1, 6, 6, 9, 0, 9, 3, 0, 8, 8, 9, 0, 2, 9, 1, 9, 0, 4, 7, 4, 2, 5, 6, 3, 0, 3, 6, 2, 6, 6, 6, 6, 6, 4, 6, 3, 1, 7, 3, 4, 7, 0, 2, 9, 5, 2, 3, 1, 2, 1, 0, 1, 1, 8, 5, 3, 1, 3, 1, 6, 9, 9, 4, 0, 7, 2, 0, 4, 7, 7, 2, 2, 3, 4, 5, 0, 9, 0, 2, 3, 1, 6, 8, 0, 4, 0, 5, 5, 9, 5, 1, 3, 5, 0, 6, 3, 6, 8, 0, 9, 2, 9, 8, 9, 0, 9, 2, 9, 8, 8, 5, 8, 2, 4, 9, 4, 1, 1, 9, 9, 4, 6, 4, 7, 2, 1, 2, 9, 3, 6, 8, 7, 7, 7, 9, 9, 1, 9, 9, 3, 3, 8, 0, 3, 4, 1, 4, 1, 3, 9, 2, 5, 6, 3, 0, 4, 8, 0, 4, 4, 5, 5, 3, 2, 9, 3, 2, 6, 5, 0, 1, 1, 3, 0, 6, 7, 6, 9, 8, 7, 1, 2, 5, 4, 0, 7, 0, 6, 4, 7, 7, 2, 8, 2, 3, 1, 1, 2, 4, 1, 0, 1, 8, 3, 8, 2, 4, 4, 6, 4, 2, 1, 3, 3, 1, 2, 1, 4, 8, 1, 2, 9, 7, 0, 4, 1, 9, 4, 3, 9, 0, 6, 2, 4, 0, 6, 2, 8, 0, 9, 8, 5, 1, 1, 1, 9, 3, 2, 1, 5, 9, 1, 6, 8, 2, 4, 5, 1, 8, 8, 2, 1, 6, 3, 6, 7, 1, 5, 0, 1, 8, 1, 5, 8, 5, 1, 6, 2, 3, 4, 3, 0, 4, 5, 4, 2, 5, 3, 2, 4, 9, 0, 4, 6, 0, 4, 4, 7, 7, 2, 2, 1, 0, 5, 1, 4, 0, 3, 9, 6, 4, 0, 5, 4, 2, 6, 5, 4, 0, 8, 5, 3, 4, 7, 2, 9, 7, 0, 0, 1, 9, 9, 7, 0, 6, 7, 6, 9, 6, 5, 6, 4, 0, 4, 9, 0, 0, 5, 2, 3, 6, 9, 2, 6, 1, 5, 6, 0, 7, 6, 6, 6, 9, 6, 1, 5, 5, 1, 3, 4, 3, 3, 1, 1, 6, 3, 2, 5, 6, 8, 1, 7, 4, 2, 1, 2, 5, 2, 6, 9, 9, 4, 2, 7, 5, 4, 2, 5, 5, 4, 2, 8, 4, 2, 0, 0, 0, 9, 6, 7, 4, 2, 1, 9, 1, 3, 3, 7, 7, 6, 1, 5, 5, 0, 2, 1, 8, 2, 1, 6, 2, 7, 5, 6, 0, 5, 4, 7, 7, 8, 8, 1, 0, 4, 8, 9, 7, 2, 5, 9, 5, 6, 9, 4, 6, 2, 7, 3, 4, 9, 4, 8, 4, 1, 3, 9, 9, 3, 7, 8, 1, 1, 9, 0, 8, 6, 9, 6, 7, 2, 8, 2, 5, 3, 5, 0, 9, 9, 0, 4, 3, 4, 2, 7, 6, 8, 9, 5, 5, 8, 0, 1, 3, 1, 1, 4, 2, 3, 6, 6, 2, 9, 1, 3, 8, 9, 6, 9, 2, 2, 5, 0, 1, 9, 1, 3, 5, 9, 7, 2, 3, 9, 4, 6, 0, 9, 5, 5, 7, 1, 9, 4, 6, 4, 7, 5, 7, 8, 1, 5, 6, 6, 8, 3, 0, 2, 0, 3, 8, 1, 2, 2, 4, 7, 1, 6, 8, 9, 4, 0, 9, 2, 0, 1, 2, 1, 9, 8, 0, 6, 4, 1, 4, 7, 5, 3, 6, 8, 7, 9, 2, 7, 0, 8, 5, 9, 4, 4, 3, 4, 5, 8, 2, 8, 8, 0, 4, 9, 3, 4, 4, 3, 6, 2, 5, 8, 7, 6, 3, 7, 2, 2, 8, 7, 0, 0, 7, 8, 7, 9, 7, 8, 5, 9, 6, 1, 8, 2, 8, 1, 6, 5, 5, 1, 3, 2, 9, 1, 2, 7, 9, 8, 4, 6, 3, 1, 4, 7, 4, 3, 2, 0, 2, 5, 4, 9, 8, 2, 1, 6, 3, 8, 8, 6, 0, 5, 6, 6, 2, 5, 7, 5, 0, 5, 6, 6, 2, 2, 6, 6, 4, 5, 7, 2, 0, 5, 5, 8, 9, 9, 3, 4, 5, 1, 2, 2, 1, 4, 7, 1, 4, 2, 7, 2, 0, 1, 0, 1, 7, 7, 3, 6, 3, 7, 9, 2, 3, 4, 3, 5, 2, 5, 1, 3, 9, 2, 4, 2, 4, 1, 9, 5, 0, 9, 2, 5, 9, 4, 3, 0, 4, 5, 4, 4, 5, 5, 7, 2, 0, 5, 7, 9, 3, 9, 5, 6, 5, 7, 3, 5, 3, 2, 9, 6, 0, 6, 7, 0, 5, 4, 1, 0, 4, 0, 4, 5, 6, 2, 4, 7, 3, 0, 2, 4, 0, 4, 3, 3, 2, 7, 4, 4, 3, 0, 5, 7, 8, 3, 7, 3, 3, 6, 7, 5, 7, 9, 0, 8, 4, 8, 5, 9, 5, 7, 2, 0, 3, 1, 4, 6, 9, 1, 2, 4, 9, 0, 7, 1, 7, 0, 6, 8, 8, 5, 1, 8, 9, 4, 5, 0, 5, 3, 7, 4, 7, 4, 1, 5, 7, 4, 1, 8, 1, 6, 9, 4, 8, 4, 1, 5, 2, 6, 8, 0, 2, 5, 2, 2, 9, 2, 4, 0, 7, 1, 1, 2, 3, 5, 3, 4, 0, 7, 9, 6, 1, 4, 3, 7, 6, 8, 4, 7, 7, 4, 4, 2, 6, 8, 8, 8, 9, 2, 9, 8, 9, 8, 2, 9, 9, 7, 9, 8, 6, 0, 9, 5, 4, 7, 4, 9, 5, 5, 8, 1, 2, 6, 1, 9, 2, 9, 6, 7, 7, 3, 7, 4, 4, 5, 0, 9, 2, 0, 5, 9, 9, 0, 2, 5, 3, 1, 0, 0, 4, 4, 0, 3, 3, 9, 0, 6, 0, 8, 1, 1, 3, 5, 0, 3, 5, 4, 6, 0, 0, 9, 3, 2, 3, 0, 8, 7, 0, 6, 2, 4, 7, 0, 8, 3, 5, 7, 7, 7, 8, 0, 8, 1, 4, 7, 5, 9, 8, 3, 9, 8, 6, 5, 1, 8, 2, 4, 8, 7, 7, 4, 9, 2, 0, 4, 9, 5, 0, 1, 5, 0, 9, 6, 3, 9, 1, 2, 7, 0, 1, 5, 2, 4, 4, 2, 1, 8, 8, 1, 2, 2, 8, 2, 6, 9, 2, 5, 3, 9, 6, 2, 4, 3, 1, 2, 0, 7, 1, 3, 6, 7, 5, 6, 1, 9, 1, 0, 4, 0, 8, 2, 0, 4, 6, 2, 4, 3, 4, 9, 4, 7, 2, 6, 1, 6, 6, 1, 6, 9, 8, 4, 8, 4, 7, 7, 1, 8, 7, 5, 8, 6, 6, 3, 3, 7, 5, 7, 0, 3, 4, 0, 5, 0, 4, 0, 7, 9, 7, 2, 6, 1, 9, 3, 9, 6, 3, 7, 0, 7, 8, 0, 5, 0, 0, 2, 3, 1, 1, 9, 1, 7, 7, 9, 1, 0, 7, 7, 3, 9, 6, 2, 8, 9, 0, 8, 9, 6, 7, 6, 4, 8, 1, 1, 8, 6, 6, 4, 2, 9, 1, 2, 8, 7, 0, 6, 7, 8, 5, 1, 7, 0, 0, 2, 1, 3, 2, 2, 8, 6, 6, 4, 4, 2, 0, 4, 6, 9, 6, 6, 6, 2, 4, 7, 3, 5, 8, 8, 3, 0, 2, 3, 5, 5, 6, 5, 5, 4, 5, 1, 2, 8, 0, 5, 8, 0, 1, 9, 5, 1, 1, 3, 2, 0, 2, 9, 4, 5, 7, 5, 6, 9, 1, 7, 0, 5, 2, 6, 3, 7, 4, 2, 8, 3, 6, 0, 4, 0, 6, 7, 3, 0, 7, 3, 7, 4, 8};
    public static int patternY[] = {5, 1, 8, 8, 3, 2, 9, 8, 6, 7, 5, 7, 6, 0, 5, 2, 6, 9, 3, 7, 8, 9, 6, 9, 0, 5, 1, 1, 9, 5, 4, 4, 6, 8, 3, 9, 0, 6, 8, 0, 6, 8, 4, 8, 2, 8, 1, 4, 5, 7, 1, 2, 6, 6, 9, 7, 1, 0, 3, 0, 6, 9, 7, 9, 7, 5, 2, 1, 2, 9, 1, 4, 9, 1, 0, 6, 2, 6, 5, 5, 7, 6, 8, 6, 4, 7, 6, 1, 7, 3, 8, 0, 9, 3, 8, 0, 0, 8, 5, 4, 7, 3, 6, 8, 9, 1, 8, 5, 1, 4, 9, 3, 7, 4, 1, 4, 3, 4, 0, 2, 1, 9, 0, 3, 0, 1, 6, 6, 5, 4, 0, 0, 7, 8, 7, 4, 0, 3, 6, 2, 6, 8, 4, 8, 8, 0, 6, 5, 5, 1, 4, 3, 2, 7, 3, 9, 5, 6, 4, 5, 3, 6, 6, 0, 8, 4, 8, 9, 6, 3, 6, 4, 6, 4, 7, 9, 0, 3, 6, 7, 8, 3, 1, 7, 1, 5, 4, 6, 7, 2, 0, 2, 4, 0, 6, 5, 1, 7, 1, 9, 9, 5, 7, 6, 8, 3, 7, 5, 7, 2, 9, 8, 0, 6, 4, 5, 0, 0, 7, 4, 0, 6, 6, 4, 7, 4, 7, 9, 5, 9, 7, 4, 3, 5, 1, 9, 9, 7, 6, 6, 4, 6, 6, 2, 0, 6, 1, 4, 0, 7, 7, 1, 2, 2, 3, 2, 6, 8, 6, 0, 7, 2, 0, 8, 6, 5, 1, 1, 6, 2, 6, 9, 7, 8, 7, 1, 8, 0, 3, 6, 8, 3, 9, 8, 7, 6, 1, 5, 9, 5, 2, 7, 9, 3, 0, 1, 7, 5, 8, 7, 5, 7, 5, 1, 7, 5, 1, 5, 7, 2, 4, 2, 6, 9, 5, 1, 7, 2, 7, 9, 4, 8, 8, 8, 7, 1, 4, 6, 3, 6, 0, 6, 3, 4, 4, 4, 1, 8, 3, 7, 5, 6, 7, 6, 7, 5, 2, 8, 7, 3, 9, 6, 9, 0, 5, 9, 0, 7, 8, 8, 5, 3, 8, 2, 7, 5, 2, 3, 1, 6, 0, 8, 4, 5, 1, 5, 4, 7, 1, 2, 6, 9, 9, 0, 9, 6, 4, 9, 3, 2, 6, 8, 1, 7, 7, 3, 8, 4, 2, 1, 6, 5, 2, 4, 1, 7, 1, 5, 8, 1, 5, 8, 0, 1, 8, 8, 3, 8, 6, 9, 0, 9, 4, 7, 7, 0, 0, 4, 2, 5, 2, 8, 6, 5, 0, 4, 4, 1, 5, 2, 7, 2, 3, 0, 6, 2, 4, 1, 2, 9, 4, 1, 8, 0, 0, 6, 1, 3, 0, 7, 6, 3, 0, 0, 7, 3, 9, 2, 8, 0, 2, 4, 0, 8, 3, 8, 4, 3, 0, 2, 9, 0, 3, 4, 9, 7, 2, 3, 8, 6, 4, 4, 7, 3, 3, 5, 9, 2, 0, 7, 5, 1, 8, 7, 7, 5, 7, 0, 0, 5, 2, 4, 3, 1, 4, 6, 3, 4, 5, 1, 7, 3, 2, 5, 9, 7, 7, 2, 4, 7, 8, 8, 2, 2, 6, 5, 0, 8, 8, 4, 7, 0, 8, 3, 0, 6, 8, 3, 8, 9, 0, 4, 0, 4, 5, 2, 0, 6, 8, 7, 4, 7, 8, 8, 7, 2, 3, 1, 4, 4, 0, 1, 2, 5, 2, 5, 1, 0, 9, 8, 1, 1, 1, 9, 0, 5, 4, 0, 4, 2, 0, 4, 8, 7, 2, 4, 8, 5, 9, 7, 0, 3, 5, 7, 4, 4, 5, 5, 5, 2, 5, 8, 8, 6, 0, 9, 0, 5, 8, 4, 1, 0, 2, 0, 0, 3, 0, 0, 2, 2, 2, 8, 3, 6, 1, 0, 0, 3, 5, 0, 4, 8, 9, 3, 1, 7, 1, 6, 5, 8, 6, 7, 4, 5, 6, 5, 9, 0, 5, 4, 9, 3, 8, 3, 7, 3, 8, 6, 8, 1, 8, 5, 5, 9, 0, 1, 9, 3, 4, 3, 0, 3, 5, 6, 9, 8, 5, 6, 5, 3, 2, 5, 4, 4, 0, 0, 2, 5, 9, 7, 8, 3, 3, 0, 0, 1, 4, 0, 8, 0, 1, 6, 6, 0, 9, 4, 0, 4, 1, 1, 4, 1, 8, 0, 2, 3, 1, 4, 7, 4, 5, 3, 4, 3, 3, 1, 5, 3, 9, 0, 1, 1, 8, 0, 8, 2, 9, 2, 7, 6, 2, 1, 1, 5, 3, 5, 9, 9, 7, 0, 3, 6, 4, 3, 0, 7, 1, 3, 6, 0, 7, 8, 1, 8, 0, 0, 6, 4, 5, 4, 6, 0, 0, 0, 7, 9, 5, 7, 5, 2, 3, 3, 2, 2, 9, 9, 1, 4, 9, 3, 7, 0, 5, 1, 3, 6, 1, 1, 7, 5, 9, 6, 7, 9, 9, 2, 1, 6, 5, 0, 7, 7, 9, 2, 7, 7, 6, 9, 7, 2, 3, 3, 8, 1, 2, 2, 2, 7, 8, 6, 1, 9, 4, 2, 6, 4, 5, 4, 5, 4, 6, 4, 3, 3, 2, 3, 6, 2, 1, 9, 1, 0, 5, 2, 4, 8, 7, 5, 7, 9, 4, 6, 7, 8, 2, 7, 2, 7, 9, 5, 9, 6, 6, 1, 0, 3, 8, 1, 3, 9, 8, 7, 1, 9, 7, 1, 1, 3, 6, 5, 1, 5, 1, 2, 5, 8, 5, 8, 7, 7, 0, 0, 8, 0, 4, 6, 5, 0, 6, 5, 6, 4, 6, 4, 2, 5, 2, 2, 6, 1, 1, 3, 1, 7, 1, 0, 2, 6, 5, 7, 8, 7, 8, 5, 1, 8, 0, 0, 0, 2, 0, 9, 0, 8, 0, 0, 8, 4, 9, 5, 0, 0, 7, 6, 2, 9, 2, 2, 6, 0, 2, 4, 3, 4, 5, 0, 7, 8, 8, 3, 1, 7, 2, 4, 1, 0, 5, 2, 0, 3, 4, 6, 4, 5, 1, 2, 6, 0, 8, 1, 0, 5, 3, 1, 0, 3, 3, 6, 7, 6, 1, 6, 5, 4, 5, 3, 1, 2, 7, 3, 0, 3, 2, 8, 9, 0, 7, 2, 1, 7, 4, 9, 8, 2, 4, 5, 4, 9, 1, 5, 5, 7, 0, 8, 8, 5, 7, 2, 6, 8, 7, 7, 2, 4, 3, 2, 7, 3, 7, 5, 0, 5, 5, 8, 8, 2, 7, 8, 5, 3, 0, 1, 3, 4, 3, 0, 2, 9, 1, 5, 1, 1, 2, 0, 0, 2, 3, 2, 0, 6, 8, 7, 4, 4, 6, 3, 8, 3, 9, 2, 7, 9, 7, 9, 5, 3, 8, 9, 7, 5, 9, 4, 4, 3, 3, 7, 3, 0, 6, 6, 6, 0, 7, 5, 3, 1, 4, 4, 7, 6, 9, 1, 2, 2, 5, 1, 5, 2, 9, 2, 3, 3, 8, 3, 0, 0, 0, 7, 5, 4, 8, 3, 7, 6, 5, 3, 6, 0, 4, 2, 4, 1, 4, 7, 4, 0, 0, 0, 5, 7, 4, 1, 1, 0, 7, 3, 7, 1, 2, 7, 4, 9, 9, 9, 6, 9, 6, 0, 5, 6, 0, 6, 1, 3, 9, 5, 0, 4, 0, 0, 3, 7, 2, 0, 1, 0, 5, 1, 7, 6, 6, 2, 7, 2, 7, 5, 9, 0, 4, 6, 3, 1, 5, 2, 4, 8, 4, 6, 3, 2, 9, 9, 1, 0, 4, 1, 7, 7, 8, 5, 4, 4, 0, 0, 9, 1, 1, 9, 7, 2, 5, 5, 4, 1, 9, 1, 4, 2, 3, 1, 9, 4, 0, 5, 3, 2, 5, 4, 0, 1, 2, 6, 0, 9, 7, 3, 0, 6, 0, 9, 8, 6, 5, 6, 0, 4, 9, 7, 6, 1, 0, 9, 0, 4, 8, 5, 3, 7, 9, 3, 1, 5, 2, 3, 2, 2, 7, 2, 6, 1, 1, 1, 3, 0, 2, 4, 8, 1, 2, 2, 2, 3, 8, 9, 0, 9, 3, 8, 1, 0, 9, 3, 0, 5, 7, 3, 4, 4, 9, 8, 3, 6, 9, 1, 5, 2, 9, 1, 0, 7, 6, 8, 7, 4, 6, 9, 5, 7, 3, 3, 3, 0, 4, 2, 5, 5, 5, 4, 8, 5, 8, 6, 5, 5, 0, 9, 3, 6, 4, 7, 8, 7, 0, 2, 9, 6, 7, 7, 6, 7, 4, 6, 8, 5, 7, 5, 0, 8, 2, 0, 1, 6, 0, 7, 6, 1, 5, 5, 7, 4, 9, 4, 2, 7, 6, 6, 8, 6, 9, 4, 4, 4, 7, 3, 4, 1, 1, 3, 0, 1, 8, 2, 3, 0, 6, 1, 4, 7, 1, 6, 9, 0, 4, 1, 1, 6, 0, 9, 1, 2, 5, 3, 0, 7, 8, 7, 0, 6, 9, 9, 2, 4, 5, 1, 3, 6, 0, 1, 7, 9, 5, 2, 3, 1, 9, 1, 7, 1, 2, 6, 8, 1, 9, 2, 1, 9, 2, 8, 0, 5, 2, 1, 3, 9, 8, 3, 1, 2, 8, 3, 7, 1, 8, 2, 7, 2, 0, 1, 6, 9, 0, 2, 2, 1, 0, 3, 8, 1, 7, 6, 2, 1, 6, 2, 3, 5, 8, 7, 9, 6, 7, 2, 3, 5, 3, 7, 2, 6, 7, 8, 2, 8, 8, 4, 8, 0, 0, 8, 5, 6, 1, 5, 8, 7, 8, 7, 9, 9, 5, 3, 8, 4, 8, 2, 1, 7, 6, 4, 9, 1, 0, 8, 0, 2, 9, 5, 3, 6, 2, 0, 5, 9, 5, 2, 6, 0, 8, 3, 9, 0, 9, 2, 5, 3, 1, 2, 8, 5, 8, 4, 9, 7, 2, 4, 0, 3, 5, 3, 6, 0, 9, 8, 1, 1, 5, 6, 5, 6, 2, 9, 0, 2, 7, 9, 4, 7, 5, 5, 7, 8, 1, 2, 9, 6, 9, 2, 3, 5, 1, 2, 6, 8, 9, 7, 8, 5, 1, 6, 9, 7, 5, 7, 9, 5, 2, 6, 8, 6, 4, 5, 8, 2, 7, 3, 2, 7, 4, 1, 6, 5, 0, 3, 6, 1, 7, 4, 9, 3, 1, 5, 3, 0, 3, 8, 7, 1, 5, 7, 2, 2, 5, 6, 9, 0, 2, 9, 8, 5, 5, 9, 2, 6, 2, 1, 3, 3, 7, 5, 6, 3, 8, 5, 5, 6, 6, 4, 7, 4, 4, 6, 9, 8, 7, 6, 2, 9, 2, 1, 4, 1, 8, 1, 3, 0, 1, 4, 8, 0, 6, 7, 9, 9, 8, 5, 2, 4, 3, 7, 5, 3, 8, 4, 7, 5, 8, 1, 5, 5, 9, 8, 9, 6, 9, 1, 5, 4, 1, 6, 9, 2, 8, 3, 6, 9, 2, 9, 7, 4, 0, 8, 5, 8, 8, 2, 0, 3, 3, 9, 5, 7, 2, 9, 1, 2, 9, 2, 8, 7, 0, 1, 3, 2, 3, 7, 2, 0, 5, 6, 7, 8, 9, 9, 9, 7, 5, 9, 9, 3, 2, 8, 6, 7, 3, 4, 9, 6, 4, 0, 0, 7, 9, 8, 4, 1, 5, 9, 0, 9, 4, 2, 1, 8, 4, 8, 2, 6, 4, 0, 9, 1, 5, 2, 3, 6, 3, 7, 7, 3, 3, 0, 1, 3, 8, 9, 6, 3, 9, 4, 8, 7, 6, 8, 1, 1, 3, 9, 1, 5, 6, 7, 9, 0, 7, 6, 0, 3, 5, 3, 2, 1, 3, 3, 4, 6, 9, 5, 6, 5, 0, 9, 9, 2, 7, 4, 3, 9, 8, 8, 5, 6, 4, 2, 4, 4, 8, 2, 6, 4, 6, 3, 4, 6, 0, 6, 9, 4, 6, 9, 8, 9, 8, 2, 4, 2, 7, 0, 2, 8, 2, 4, 0, 1, 1, 8, 1, 8, 4, 1, 1, 6, 0, 4, 2, 7, 5, 6, 5, 6, 9, 7, 5, 9, 0, 8, 6, 6, 0, 6, 4, 0, 9, 4, 7, 0, 7, 4, 9, 5, 6, 8, 0, 9, 0, 7, 4, 4, 2, 2, 3, 9, 9, 2, 3, 9, 5, 8, 1, 0, 2, 2, 9, 9, 7, 2, 2, 0, 0, 9, 3, 7, 5, 7, 6, 2, 3, 6, 1, 8, 6, 0, 0, 9, 8, 6, 0, 9, 2, 8, 3, 4, 6, 5, 3, 3, 3, 3, 1, 0, 9, 0, 7, 4, 4, 8, 3, 8, 0, 1, 0, 6, 1, 2, 8, 5, 2, 5, 5, 8, 0, 5, 6, 4, 3, 0, 1, 5, 2, 9, 1, 2, 4, 0, 6, 5, 2, 2, 7, 9, 0, 1, 4, 0, 9, 5, 5, 1, 7, 8, 4, 4, 3, 2, 6, 6, 0, 3, 4, 6, 6, 9, 3, 6, 6, 7, 9, 2, 1, 2, 4, 5, 6, 8, 1, 5, 4, 4, 7, 9, 7, 3, 9, 8, 8, 5, 4, 2, 3, 1, 2, 0, 9, 1, 7, 9, 3, 9, 4, 9, 8, 1, 7, 0, 0, 0, 0, 6, 4, 7, 9, 3, 1, 0, 5, 9, 5, 0, 0, 6, 2, 3, 8, 6, 6, 7, 2, 3, 5, 7, 5, 3, 2, 3, 1, 4, 6, 2, 7, 7, 7, 6, 3, 9, 3, 0, 8, 5, 4, 8, 5, 1, 2, 1, 4, 8, 1, 5, 0, 4, 7, 6, 2, 4, 7, 3, 6, 4, 3, 0, 1, 9, 9, 0, 7, 1, 7, 3, 6, 4, 1, 9, 3, 7, 6, 4, 5, 2, 7, 2, 9, 7, 4, 8, 9, 4, 6, 2, 1, 8, 5, 2, 9, 6, 1, 9, 9, 3, 8, 7, 0, 2, 9, 5, 4, 0, 0, 4, 3, 7, 8, 5, 5, 5, 8, 9, 1, 2, 4, 5, 4, 9, 8, 5, 6, 2, 8, 5, 7, 7, 6, 1, 7, 8, 1, 8, 1, 7, 1, 0, 9, 6, 5, 7, 7, 7, 8, 5, 9, 5, 1, 2, 7, 8, 1, 3, 3, 6, 6, 7, 8, 6, 7, 2, 8, 6, 5, 0, 5, 8, 5, 4, 6, 3, 4, 9, 5, 9, 7, 0, 2, 5, 4, 0, 8, 2, 9, 3, 5, 8, 2, 1, 5, 6, 9, 1, 3, 9, 4, 1, 5, 0, 7, 8, 2, 2, 4, 8, 1, 4, 7, 6, 5, 2, 8, 7, 5, 4, 3, 7, 8, 8, 7, 5, 2, 8, 2, 3, 1, 1, 9, 2, 6, 7, 5, 3, 2, 0, 6, 9, 1, 9, 4, 8, 2, 1, 5, 6, 5, 9, 9, 4, 6, 7, 9, 1, 7, 1, 2, 3, 4, 9, 5, 1, 5, 7, 0, 8, 3, 4, 8, 8, 9, 4, 6, 6, 9, 0, 7, 3, 3, 4, 4, 3, 0, 7, 9, 0, 5, 6, 4, 4, 3, 6, 1, 3, 1, 0, 5, 4, 2, 8, 7, 3, 0, 8, 1, 4, 9, 7, 1, 6, 0, 4, 4, 8, 7, 0, 6, 0, 9, 7, 4, 7, 7, 8, 2, 2, 1, 5, 1, 6, 7, 1, 2, 0, 2, 0, 5, 2, 0, 0, 0, 5, 7, 7, 2, 6, 6, 3, 9, 3, 7, 6, 1, 7, 4, 7, 3, 8, 0, 1, 3, 8, 9, 4, 5, 2, 8, 4, 9, 9, 4, 0, 3, 2, 6, 0, 9, 7, 4, 8, 4, 7, 9, 5, 4, 1, 1, 6, 6, 5, 6, 8, 2, 1, 6, 0, 8, 7, 1, 6, 9, 2, 4, 5, 7, 0, 1, 9, 5, 8, 0, 9, 8, 6, 4, 8, 8, 3, 0, 3, 3, 6, 1, 4, 0, 4, 6, 1, 1, 4, 7, 4, 0, 4, 2, 2, 8, 9, 7, 9, 3, 5, 5, 9, 3, 8, 1, 2, 5, 0, 4, 7, 9, 7, 4, 4, 0, 7, 8, 3, 9, 3, 3, 8, 9, 4, 6, 0, 3, 5, 7, 4, 4, 8, 7, 2, 8, 2, 4, 7, 9, 0, 9, 1, 9, 8, 6, 5, 2, 1, 9, 0, 2, 9, 7, 2, 6, 1, 9, 4, 7, 9, 2, 3, 5, 8, 4, 4, 0, 7, 1, 9, 5, 6, 8, 4, 2, 7, 9, 9, 8, 0, 0, 4, 8, 8, 5, 5, 0, 4, 8, 6, 0, 1, 8, 7, 8, 9, 2, 6, 1, 9, 2, 2, 5, 2, 1, 2, 5, 1, 4, 4, 9, 9, 6, 9, 9, 5, 6, 0, 9, 1, 8, 8, 7, 1, 2, 0, 7, 2, 6, 9, 7, 7, 5, 3, 0, 3, 1, 8, 3, 1, 8, 6, 1, 2, 3, 6, 7, 7, 5, 0, 3, 3, 2, 5, 4, 6, 2, 0, 3, 5, 6, 7, 4, 5, 7, 0, 4, 6, 4, 4, 6, 3, 0, 7, 2, 3, 6, 2, 6, 0, 7, 6, 0, 7, 0, 0, 3, 8, 4, 9, 9, 7, 3, 4, 7, 6, 9, 4, 4, 6, 6, 5, 9, 3, 6, 2, 7, 8, 6, 6, 7, 0, 7, 5, 5, 7, 5, 3, 8, 1, 9, 5, 1, 2, 2, 6, 5, 1, 4, 9, 3, 0, 9, 1, 3, 7, 5, 6, 2, 0, 0, 0, 4, 2, 4, 8, 3, 0, 2, 6, 4, 0, 5, 6, 7, 1, 2, 5, 5, 9, 8, 8, 8, 6, 2, 9, 4, 7, 1, 3, 9, 9, 6, 3, 8, 9, 4, 9, 6, 7, 5, 8, 1, 7, 6, 2, 8, 5, 1, 9, 4, 0, 2, 6, 6, 6, 5, 9, 8, 9, 7, 2, 8, 6, 1, 2, 8, 0, 4, 9, 9, 5, 4, 1, 6, 3, 1, 8, 4, 8, 1, 8, 8, 9, 6, 8, 0, 3, 2, 5, 1, 3, 3, 8, 5, 8, 4, 7, 3, 6, 3, 9, 2, 1, 5, 2, 7, 8, 0, 9, 1, 7, 4, 5, 3, 8, 4, 9, 4, 0, 3, 5, 6, 8, 8, 7, 6, 6, 4, 2, 7, 9, 6, 2, 2, 4, 7, 8, 8, 8, 5, 8, 8, 9, 4, 0, 7, 6, 0, 0, 0, 3, 6, 9, 2, 2, 2, 5, 6, 2, 9, 0, 0, 1, 3, 8, 5, 9, 9, 1, 5, 7, 3, 5, 9, 4, 1, 0, 5, 1, 9, 5, 7, 6, 4, 8, 8, 7, 9, 6, 4, 1, 4, 3, 3, 9, 2, 4, 4, 0, 8, 5, 7, 5, 9, 2, 7, 1, 1, 6, 0, 8, 4, 4, 9, 3, 0, 8, 7, 1, 9, 8, 6, 4, 7, 7, 9, 5, 7, 8, 2, 1, 7, 3, 3, 7, 1, 5, 1, 1, 1, 1, 7, 9, 2, 2, 1, 9, 9, 3, 7, 2, 4, 3, 7, 6, 0, 4, 6, 4, 4, 4, 7, 8, 3, 3, 7, 5, 6, 8, 9, 9, 3, 9, 4, 9, 3, 7, 1, 4, 4, 8, 3, 8, 7, 5, 4, 8, 2, 4, 9, 8, 6, 9, 5, 7, 7, 9, 1, 6, 2, 5, 0, 8, 3, 2, 7, 4, 9, 8, 2, 8, 1, 1, 7, 3, 5, 5, 9, 2, 2, 9, 7, 2, 1, 3, 6, 2, 6, 7, 6, 9, 4, 1, 6, 2, 6, 5, 1, 1, 1, 1, 4, 9, 3, 8, 8, 7, 8, 2, 3, 6, 1, 8, 9, 1, 9, 7, 9, 7, 6, 3, 7, 8, 0, 9, 6, 4, 3, 0, 4, 1, 5, 0, 1, 2, 2, 0, 8, 4, 1, 9, 4, 9, 9, 3, 5, 8, 9, 7, 4, 5, 9, 2, 7, 2, 2, 8, 9, 9, 3, 7, 1, 3, 3, 2, 8, 5, 0, 5, 5, 3, 7, 2, 9, 1, 2, 9, 8, 4, 2, 7, 9, 4, 8, 4, 4, 2, 1, 9, 6, 3, 8, 7, 2, 6, 8, 6, 9, 7, 6, 2, 3, 9, 4, 5, 5, 8, 7, 3, 0, 0, 2, 1, 3, 3, 9, 2, 1, 7, 7, 8, 2, 1, 8, 7, 2, 1, 3, 0, 6, 9, 2, 9, 8, 8, 0, 3, 3, 2, 6, 9, 3, 5, 8, 9, 0, 6, 6, 2, 2, 5, 5, 7, 2, 5, 5, 9, 5, 6, 0, 3, 4, 7, 5, 5, 7, 1, 5, 3, 2, 7, 0, 8, 0, 1, 0, 5, 8, 1, 8, 7, 3, 0, 5, 8, 0, 0, 0, 8, 6, 7, 5, 4, 2, 4, 0, 0, 7, 1, 4, 9, 8, 0, 4, 2, 1, 4, 8, 4, 5, 7, 0, 0, 5, 7, 6, 3, 5, 7, 9, 8, 8, 3, 0, 9, 0, 4, 3, 3, 0, 1, 0, 4, 5, 4, 3, 9, 4, 9, 1, 1, 2, 4, 8, 7, 5, 4, 8, 4, 0, 2, 2, 1, 6, 4, 4, 3, 4, 4, 3, 8, 3, 2, 7, 2, 5, 1, 2, 3, 0, 9, 0, 7, 3, 7, 9, 7, 4, 7, 0, 7, 0, 3, 4, 7, 0, 9, 0, 2, 0, 8, 6, 5, 4, 8, 1, 1, 3, 6, 7, 8, 9, 6, 0, 2, 9, 8, 6, 8, 3, 0, 1, 0, 9, 2, 4, 8, 9, 7, 3, 5, 4, 7, 5, 4, 3, 8, 6, 8, 3, 7, 2, 0, 5, 0, 0, 4, 5, 0, 8, 1, 4, 8, 8, 5, 9, 4, 4, 6, 2, 0, 7, 9, 6, 6, 7, 5, 5, 2, 6, 8, 5, 9, 0, 1, 4, 9, 2, 5, 4, 9, 9, 8, 2, 2, 5, 0, 7, 7, 7, 8, 1, 3, 7, 0, 4, 3, 4, 9, 2, 6, 4, 0, 1, 0, 5, 1, 2, 4, 9, 5, 0, 6, 3, 7, 0, 0, 6, 4, 2, 7, 2, 0, 5, 2, 6, 7, 5, 3, 3, 6, 1, 5, 9, 0, 7, 9, 4, 2, 4, 0, 5, 0, 1, 3, 2, 8, 0, 2, 9, 6, 4, 6, 6, 1, 3, 3, 7, 5, 0, 4, 4, 5, 7, 8, 0, 9, 8, 1, 6, 8, 8, 0, 9, 0, 1, 1, 5, 1, 7, 8, 0, 0, 2, 5, 0, 6, 4, 5, 1, 6, 3, 4, 9, 3, 5, 7, 2, 3, 5, 0, 4, 1, 8, 6, 8, 9, 4, 8, 7, 1, 1, 7, 2, 9, 2, 1, 4, 6, 4, 6, 9, 3, 0, 8, 6, 6, 8, 0, 7, 0, 7, 1, 9, 3, 6, 5, 6, 6, 5, 6, 5, 2, 1, 4, 2, 6, 3, 8, 9, 7, 6, 2, 6, 2, 5, 8, 1, 8, 2, 6, 0, 6, 4, 4, 3, 8, 9, 8, 5, 5, 7, 9, 5, 7, 6, 9, 2, 8, 5, 2, 3, 2, 2, 0, 6, 8, 7, 1, 4, 5, 7, 0, 8, 6, 7, 7, 3, 3, 3, 6, 7, 7, 8, 5, 1, 3, 0, 5, 9, 6, 0, 2, 0, 9, 9, 5, 8, 3, 1, 5, 7, 8, 2, 9, 6, 7, 7, 5, 0, 5, 5, 1, 8, 5, 7, 5, 5, 3, 2, 9, 3, 2, 7, 5, 4, 7, 9, 7, 9, 2, 4, 7, 0, 7, 4, 0, 7, 4, 0, 9, 6, 3, 0, 1, 1, 5, 6, 1, 9, 0, 1, 4, 7, 0, 0, 2, 2, 4, 3, 0, 8, 6, 1, 2, 4, 7, 6, 1, 9, 7, 7, 4, 3, 6, 9, 7, 7, 9, 6, 3, 1, 7, 0, 2, 1, 8, 6, 3, 9, 8, 5, 9, 4, 3, 7, 9, 0, 4, 5, 6, 1, 0, 1, 3, 0, 2, 4, 6, 1, 9, 4, 9, 4, 2, 3, 0, 1, 0, 2, 7, 6, 8, 2, 6, 3, 0, 1, 2, 8, 4, 0, 5, 4, 7, 5, 1, 2, 7, 8, 1, 0, 8, 2, 1, 1, 1, 7, 7, 8, 2, 8, 5, 7, 1, 9, 3, 1, 0, 5, 4, 6, 4, 8, 7, 6, 4, 9, 7, 5, 0, 6, 8, 1, 6, 2, 3, 8, 1, 6, 1, 5, 4, 5, 3, 8, 1, 0, 4, 5, 2, 5, 8, 0, 2, 3, 4, 2, 0, 1, 6, 0, 4, 5, 3, 0, 7, 8, 8, 5, 9, 9, 3, 5, 8, 2, 7, 7, 3, 4, 6, 5, 2, 2, 2, 5, 3, 4, 6, 0, 3, 9, 4, 9, 1, 9, 2, 8, 0, 2, 8, 1, 2, 9, 3, 9, 9, 1, 3, 8, 6, 3, 5, 0, 0, 5, 0, 7, 3, 6, 7, 0, 1, 5, 7, 9, 0, 4, 8, 9, 2, 1, 2, 4, 2, 3, 1, 4, 1, 0, 1, 8, 8, 1, 8, 7, 9, 0, 7, 9, 6, 8, 7, 1, 1, 8, 0, 5, 8, 5, 5, 0, 2, 5, 8, 0, 3, 5, 9, 8, 3, 0, 8, 0, 4, 0, 5, 9, 1, 2, 7, 9, 1, 4, 6, 1, 3, 5, 3, 7, 4, 7, 4, 6, 3, 6, 1, 7, 7, 6, 2, 2, 1, 3, 8, 7, 3, 0, 1, 1, 8, 8, 3, 7, 1, 0, 2, 0, 8, 8, 8, 3, 7, 7, 2, 9, 3, 4, 1, 3, 7, 3, 7, 7, 1, 9, 2, 2, 8, 3, 1, 8, 4, 2, 3, 6, 9, 8, 4, 4, 5, 9, 1, 0, 0, 9, 7, 7, 1, 8, 6, 0, 2, 8, 9, 8, 2, 5, 1, 1, 0, 5, 0, 7, 0, 4, 9, 2, 6, 9, 2, 8, 0, 3, 2, 9, 8, 6, 8, 0, 9, 0, 9, 4, 8, 9, 9, 6, 7, 1, 9, 8, 4, 3, 1, 9, 5, 0, 3, 6, 3, 7, 1, 5, 3, 1, 5, 8, 3, 0, 4, 7, 2, 5, 6, 6, 1, 5, 4, 6, 0, 1, 5, 7, 4, 0, 1, 0, 1, 3, 6, 5, 0, 2, 4, 6, 8, 2, 1, 9, 9, 2, 9, 7, 4, 7, 1, 3, 3, 3, 9, 1, 4, 5, 7, 3, 5, 6, 8, 7, 4, 9, 7, 2, 5, 8, 7, 5, 7, 8, 1, 0, 1, 5, 0, 6, 1, 1, 0, 1, 8, 2, 3, 2, 6, 6, 6, 8, 8, 4, 3, 9, 5, 9, 9, 8, 2, 4, 7, 8, 2, 0, 9, 1, 2, 6, 8, 0, 0, 5, 2, 3, 4, 2, 9, 8, 2, 7, 4, 1, 7, 8, 4, 3, 7, 9, 6, 5, 5, 9, 9, 2, 7, 2, 3, 0, 2, 2, 2, 2, 8, 4, 0, 1, 4, 5, 8, 9, 6, 8, 2, 2, 3, 3, 7, 8, 6, 9, 4, 4, 2, 0, 6, 9, 5, 3, 3, 4, 2, 8, 1, 0, 7, 0, 9, 0, 4, 9, 3, 9, 4, 8, 0, 4, 2, 9, 7, 1, 2, 8, 6, 5, 6, 2, 5, 9, 6, 7, 4, 2, 8, 5, 4, 2, 5, 9, 5, 7, 9, 9, 9, 9, 8, 7, 3, 9, 2, 2, 6, 8, 6, 5, 1, 6, 9, 2, 6, 4, 0, 1, 4, 0, 4, 8, 0, 0, 1, 1, 8, 0, 1, 1, 7, 8, 7, 8, 6, 5, 4, 0, 3, 4, 8, 3, 2, 6, 7, 1, 7, 7, 4, 4, 7, 2, 1, 2, 4, 8, 6, 3, 6, 4, 0, 4, 9, 1, 4, 2, 8, 5, 5, 1, 5, 1, 2, 9, 2, 6, 6, 9, 8, 0, 3, 1, 7, 1, 5, 4, 1, 4, 7, 4, 9, 8, 5, 4, 8, 5, 1, 1, 6, 4, 2, 2, 2, 5, 1, 5, 6, 9, 4, 5, 0, 1, 8, 4, 2, 8, 6, 6, 2, 2, 8, 3, 6, 8, 1, 5, 0, 6, 9, 7, 2, 9, 3, 7, 2, 7, 6, 8, 3, 1, 4, 2, 3, 4, 7, 0, 3, 5, 4, 5, 6, 2, 7, 9, 6, 6, 9, 6, 2, 0, 0, 8, 3, 3, 1, 2, 8, 5, 1, 6, 0, 7, 7, 6, 4, 3, 2, 6, 0, 8, 9, 3, 8, 0, 1, 7, 4, 8, 9, 5, 8, 0, 5, 3, 8, 7, 7, 6, 6, 8, 4, 1, 2, 3, 5, 6, 2, 5, 3, 1, 4, 4, 4, 0, 8, 4, 9, 3, 0, 4, 1, 4, 7, 1, 1, 8, 5, 2, 0, 7, 5, 0, 4, 6, 1, 5, 3, 9, 1, 1, 3, 9, 1, 5, 0, 6, 1, 9, 6, 0, 4, 8, 3, 0, 4, 0, 4, 6, 1, 4, 4, 0, 1, 6, 8, 7, 1, 9, 3, 8, 2, 3, 7, 1, 9, 0, 7, 5, 0, 1, 2, 7, 6, 9, 9, 8, 3, 5, 8, 4, 8, 9, 9, 5, 0, 7, 8, 9, 6, 7, 4, 1, 7, 0, 8, 1, 2, 1, 7, 9, 9, 0, 8, 1, 7, 3, 5, 4, 8, 0, 3, 2, 0, 5, 4, 6, 8, 1, 4, 2, 2, 3, 0, 0, 0, 9, 0, 1, 9, 1, 8, 9, 5, 0, 8, 5, 7, 7, 7, 7, 9, 6, 4, 7, 3, 2, 8, 8, 1, 3, 2, 0, 8, 6, 8, 5, 1, 0, 8, 6, 1, 1, 5, 6, 3, 4, 3, 6, 5, 2, 2, 5, 4, 5, 5, 8, 3, 6, 5, 2, 7, 4, 3, 1, 2, 2, 2, 5, 9, 5, 2, 5, 1, 0, 5, 8, 5, 7, 1, 1, 8, 3, 1, 3, 8, 1, 1, 5, 9, 9, 7, 9, 9, 7, 3, 1, 0, 7, 7, 4, 6, 7, 2, 2, 6, 0, 6, 9, 8, 8, 2, 4, 2, 7, 0, 8, 1, 5, 2, 3, 0, 8, 5, 1, 3, 6, 3, 5, 2, 1, 3, 2, 5, 4, 7, 1, 3, 8, 5, 7, 1, 2, 0, 9, 0, 9, 9, 5, 9, 8, 8, 0, 2, 7, 2, 3, 9, 3, 9, 1, 1, 7, 2, 8, 8, 4, 2, 0, 0, 8, 7, 0, 5, 6, 5, 2, 1, 1, 5, 4, 6, 2, 5, 9, 4, 5, 9, 1, 9, 6, 5, 4, 2, 9, 9, 1, 7, 2, 7, 2, 0, 4, 0, 4, 6, 7, 0, 3, 2, 3, 5, 7, 7, 3, 5, 2, 1, 2, 4, 8, 6, 0, 7, 7, 9};
    public static int indivMissing;
    public static int dataIndex;
    public static int anzahl;
	public boolean randomizeFunction;
	public ArrayList<Double> weightsBefore;
	
	GameCopy() {
	}
	
	public static void rollDice(AI ind){
		ind.dice = Math.random() * ind.fitness;
	}
	
	public static void deleteDice(AI ind){
		ind.dice = 0;
	}

	public static void setGenerations(int i){
		xMax = i;
	}
	
	public static void setMoves(int i){
		maxMoves = i;
	}
	
	public int getMaxMoves(){
		return maxMoves;
	}
	
	public static void write (String filename, ArrayList<Double>p) throws IOException{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  for (int i = 0; i < p.size(); i++) {
		    outputWriter.write(Double.toString((p.get(i))));
		    outputWriter.newLine();
		  }
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public void load(int u){
	}
	
	public static void oneStep(int player){
		
		individual = individuals.get(player);
		for (int k=0;k<100;k++){
        	individual.setInput(k,0);
        	}
        	
        	individual.setInput(myBoard.food.getFoodY()*10 + myBoard.food.getFoodX(), 1);
        	
        	 for (int k=myBoard.snake.getJoints(); k>0;k--) {
        		 
        		 individual.setInput(myBoard.snake.getSnakeY(k)* 10 + myBoard.snake.getSnakeX(k), -2);
        	 }
        	 
individual.setInput(myBoard.snake.getSnakeY(0)* 10 + myBoard.snake.getSnakeX(0), -1);
        	 
        	 // evaluate output
        	 individual.Layers[1].updateLayer();
        	 individual.Layers[2].updateLayer();
        	 
        	 // get output
        	 double outputUp = individual.getOutput(0);
        	 double outputDown = individual.getOutput(1);
        	 double outputLeft = individual.getOutput(2);
        	 double outputRight = individual.getOutput(3);
        	 
        	 if ((outputUp>outputDown)&&(outputUp>outputLeft)&&(outputUp>outputRight)&&(!myBoard.snake.isMovingDown())){
        		myBoard.snake.setMovingUp(true);
 	            myBoard.snake.setMovingRight(false);
 	            myBoard.snake.setMovingLeft(false);
 	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputRight>outputDown)&&(outputRight>outputLeft)&&(outputRight>outputUp)&&(!myBoard.snake.isMovingLeft())){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(true);
  	            myBoard.snake.setMovingLeft(false);
  	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputLeft>outputDown)&&(outputLeft>outputUp)&&(outputLeft>outputRight)&&(!myBoard.snake.isMovingRight())){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(false);
  	            myBoard.snake.setMovingLeft(true);
  	            myBoard.snake.setMovingDown(false);
        	 }
        	 
        	 if ((outputDown>outputUp)&&(outputDown>outputLeft)&&(outputDown>outputRight)&&(!myBoard.snake.isMovingUp())){
        		myBoard.snake.setMovingUp(false);
  	            myBoard.snake.setMovingRight(false);
  	            myBoard.snake.setMovingLeft(false);
  	            myBoard.snake.setMovingDown(true);
        	 }
        	 
        	 myBoard.actionPerformed(null);
	}

	public static void main(String[] args) {
		
//change here
		anzahl = 1000;
		dataIndex=1;
		for (int t=0;t<dataIndex;t++){
		
		for (int day7=0;day7<100;day7++){
			individual = new AI();
			individual.initialize();
			individuals.add(individual);
		}
		
		generationScores = new ArrayList<Double>();
		setGenerations(10*anzahl);
		
		for (xCurrent=0;xCurrent<xMax;xCurrent++){
	
		for (int a=0;a<100;a++){
		
			myBoard = new Board();
		
			moves =0;
			setMoves(5000);
			while ((myBoard.inGame==true)){
				oneStep(a);
				moves++;
				if (moves > maxMoves){
					myBoard.inGame = false;
				}
			} 
	    
			individuals.get(a).setScore(myBoard.getScore());
			individuals.get(a).setMovesToScore(myBoard.movesTo2ndLast);
			individuals.get(a).Fitness();   
		}
				
		int totalscore=0;
		for (int k=0;k<100;k++){
			totalscore += individuals.get(k).score;
		}
		generationScores.add((double)totalscore);
		
		System.out.println("gen "+xCurrent+" total "+totalscore);
		
		LinkedList<AI> newGen = new LinkedList<AI>();
		for (int i=0;i<100;i++){
			for (int j=0;j<100;j++){
				rollDice(individuals.get(j));
			}
			Collections.sort(individuals);
			AI indiv1 = individuals.get(0);
			AI indiv2 = individuals.get(1);
			newIndiv = new AI();
			newIndiv.initialize();
			newIndiv.crossover(newIndiv, indiv1, indiv2, newGen); 
		}
		
		indivMissing = 100 - newGen.size();
		for (int i=0;i<indivMissing;i++){
			AI individual = new AI();
			individual.initialize();
			newGen.add(individual);
		} 
		
		individuals = newGen;
		
		if (xCurrent==(xMax-1)){
			AI best = individuals.get(0);
			int bestScore = individuals.get(0).score;
			for (int i=1;i<100;i++){
				if ((individuals.get(i).score)>(bestScore)){
					best = individuals.get(i);
					bestScore = individuals.get(i).score;
				}
			}	

			Double Qgen = 0.0;
			int correct =0;
			ArrayList<Double>excelScores;
			excelScores = new ArrayList<Double>();
			for (int e=1;e<11;e++){
				if (e>=2){
					correct = 1;
				}
				for (int f=(e-1)*anzahl-correct;f<e*anzahl;f++){
					Qgen = Qgen+generationScores.get(f);
				}
				Qgen = Qgen/anzahl;
				excelScores.add(Qgen);
				Qgen = 0.0;
			}

			
			ArrayList<Double>Numbers;
			Numbers = new ArrayList<Double>();
			Numbers.add((double)bestScore);
			/*for (int layer=1;layer<3;layer++){
				for (int n=0;n<best.Layers[layer].Neurons.size();n++){
					
					ArrayList<Double>FinalWeights;
					FinalWeights = new ArrayList<Double>();
					for (int w=0;w<best.Layers[layer].Neurons.get(n).Weights.size();w++){
						FinalWeights.add(best.Layers[layer].Neurons.get(n).Weights.get(w));
					} try {
						GameCopy.write("AI_gen("+xCurrent+")_layer("+layer+")_neuron("+n+")", FinalWeights);
						System.out.println(FinalWeights);
						System.out.println(best.score);
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			}
			try{
				GameCopy.write("generationScores", generationScores);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			try{
				GameCopy.write(dataIndex+" xcel (3000_10*300)", excelScores);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
		
}
		ArrayList<Double>aiConst;
		aiConst = new ArrayList<Double>();
		aiConst.add(individual.uniformRate);
		aiConst.add(individual.crossoverProb);
		aiConst.add(individual.mutationConst);
		aiConst.add(individual.mutationRate);
		
		try{
			GameCopy.write("Constants for AI", aiConst);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}