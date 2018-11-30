package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    // Muutoksia tiedostoon tehtävää 3 vareten
    
    public String getScore() {
        if (m_score1 == m_score2) { // If the score is even
            return even(m_score1);
        } else if (m_score1 >= 4 || m_score2 >= 4) { // If the score is more than or equal to 4 and not equal
            return end(m_score1, m_score2);
        }
        return scoring(); // Handles rest of the cases
    }

    private String even(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    private String end(int score1, int score2) {
        int minusResult = score1 - score2;
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String scoring() {
        return getPlayerScore(m_score1) + "-" + getPlayerScore(m_score2);
    }

    private String getPlayerScore(int score) {
        switch (score) {
            default:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
    }
}
