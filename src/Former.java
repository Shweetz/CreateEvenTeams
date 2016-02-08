import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class Former {

	public final boolean DEBUG = true;
	public final int DIV = 0;
	public final int SCORE = 1;
	public final int NAME = 2;
	public final int TEAM = 3;
	
	int nbJoueurs = 0;
	int nbEquipes = 0;
	int tailleEquipe = 5;
	int niveauMoyen = 0;
	int[] teamPoints;
	String[][] tab;
	Map<String, Integer> tierValueMap = new HashMap<String, Integer>();
	boolean doAPermutation = true;
	String result = "";
	
	public Former(String fileName, int taille)
	{
		tailleEquipe = taille;
		
		initializeMap();
		
		extractDataFromFile(fileName);
		
		calculateTeamPoints();
		
		while (doAPermutation)
			doPermutations();
		
		printResult();
	}

	private void printResult() {
		for (int i = 1; i < nbEquipes + 1; ++i)
		{
			result += "\nTeam " + i + ", " + teamPoints[i] + " pts\n";
			
			for (int j = 0; j < nbJoueurs; ++j)
			{
				if (i == Integer.parseInt(tab[j][TEAM]))
					result += tab[j][DIV] + " " + tab[j][NAME] + "\n";
			}
		}
		
		System.out.println(result);
	}

	private void doPermutations() {		
		doAPermutation = false;
		
		// On essaie une permutation
		for (int i = 0; i < nbEquipes * tailleEquipe; ++i)
		{
			int teamA = Integer.parseInt(tab[i][TEAM]);
			int scoreA = Integer.parseInt(tab[i][SCORE]);
			
			for (int j = i+1; j < nbJoueurs; ++j)
			{
				int teamB = Integer.parseInt(tab[j][TEAM]);		
				int scoreB = Integer.parseInt(tab[j][SCORE]);		
				
				int diff = Math.abs(teamPoints[teamA] - teamPoints[teamB]);				
				int diffPerm = Math.abs(teamPoints[teamA] - teamPoints[teamB] - 2*scoreA + 2*scoreB);

				// Si ELO plus proche, permuter
				if (diffPerm < diff)
				{
					if (DEBUG)
					{
						System.out.println("switching player " + (tab[i][NAME]) + " (" + tab[i][SCORE] + " pts) "
								+ "in team " + tab[i][TEAM] + " (" 
								+ teamPoints[Integer.parseInt(tab[i][TEAM])] + " pts) "
										+ "with player " + (tab[j][NAME]) + " (" + tab[j][SCORE] + " pts) "
								+ "in team " + tab[j][TEAM] + " (" 
								+ teamPoints[Integer.parseInt(tab[j][TEAM])] + " pts)");
					}
					
					String tempTeam = tab[j][TEAM];
					tab[j][TEAM] = tab[i][TEAM];
					tab[i][TEAM] = tempTeam;
					
					// Recalculer ELO total de team
					teamPoints[teamA] = teamPoints[teamA] - scoreA + scoreB;
					teamPoints[teamB] = teamPoints[teamB] + scoreA - scoreB;
					
					doAPermutation = true;
					
				}
			}
		}
	}
	
	private void calculateTeamPoints() {		
		teamPoints = new int[nbEquipes+1];		
		
		// On calcule les points de team
		for (int i = 0; i < nbJoueurs; ++i)
		{
			teamPoints[Integer.parseInt(tab[i][TEAM])] += Integer.parseInt(tab[i][SCORE]);
			
			if (DEBUG)
			{
				System.out.println("adding player " + (tab[i][NAME]) + " (" + tab[i][SCORE] + " pts) "
						+ "to team " + tab[i][TEAM] + ", which sum is now " 
						+ teamPoints[Integer.parseInt(tab[i][TEAM])] + " pts");
			}
		}
	}	
	
	private void extractDataFromFile(String fileName) {			
		try {
			// On compte le nombre de lignes du fichier
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			String ligne;
			
			for (int i = 0; (ligne = raf.readLine()) != null; ++i)
			{
				if (ligne.length() > 2)
					nbJoueurs = i+1;
			}
			raf.close();
			
			// On remplit le tableau avec des joueurs
			nbEquipes = nbJoueurs/tailleEquipe;
			tab = new String[nbJoueurs][4];
			
			raf = new RandomAccessFile(fileName, "r");
			
			for (int i = 0; (ligne = raf.readLine()) != null; ++i) 
			{
				String[] mots = ligne.split(" ");
				if (mots.length == 2)
				{
					tab[i][DIV] = mots[0];
					tab[i][SCORE] = Integer.toString(tierValueMap.get(mots[0]));
					tab[i][NAME] = mots[1];
					
					// Affectation à une équipe
					if (i < (tailleEquipe * nbEquipes))
						tab[i][TEAM] = Integer.toString((i % nbEquipes) + 1); 
					else
						tab[i][TEAM] = Integer.toString(0); 
				}
			}
			raf.close();
			
		}
		catch (Exception e) {
			System.out.print(fileName + " not found: " + e.toString() + "\n");		
		}
	}
	
	private void initializeMap() {			
		String fileName = "map.txt";
		
		try {
			// On compte le nombre de lignes du fichier
			RandomAccessFile raf = new RandomAccessFile(fileName, "r");
			String ligne;
			
			for (; (ligne = raf.readLine()) != null;) 
			{
				String[] mots = ligne.split(" ");
				
				tierValueMap.put(mots[0], Integer.parseInt(mots[1]));
			}
			raf.close();
			
		}
		catch (Exception e) {
			System.out.print(fileName + " not found: " + e.toString() + "\n");		
		}		
	}
	
	public static void main(String[] args)
	{
		new Former("inputMain.txt", 2);
	}
}
