package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.ReplayController;

public class LogicGame {
	private InputStream in;
	private List<String> words = new ArrayList<>();
	private String score;
	

	/**
	 * Initialize new game.
	 * 
	 */
	public LogicGame() {
		in = null;
		if (in == null) {
			in = this.getClass().getResourceAsStream("/text/usa.txt");
		}
		if (in == null)
			throw new RuntimeException();
		words = readWords();
	}

	public List<String> readWords() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		List<String> list = new ArrayList<String>();
		while (true) {
			String word = "";
			try {
				word = reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			if (word == null)
				break;
			list.add(word.trim().toLowerCase());
		}
		return list;
	}

	public String getWord() {
		Random random = new Random();
		return words.get(random.nextInt(words.size() - 1));
	}

	public static void main(String[] args) {
		LogicGame game = new LogicGame();
		System.out.println(game.getWord());
	}

//	public String getScore() {
//		return score;
//	}
//	
//	public void setScore(String score) {
//		this.score = score;
//	}
}
