package tictactoe.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class Game {

    public enum PlayerType {
        HUMAN,
        COMPUTER;
    }

    public enum PlayerNumber {
        PLAYER_1,
        PLAYER_2
    }

    public enum GameState {
        IN_PROGRESS,
        PLAYER_1_WIN,
        PLAYER_2_WIN,
        DRAW
    }

    @Id
    @GeneratedValue
    private Long id;

    private int boardSize;

    @ManyToOne
    private AppUser appUser;

    private PlayerType player1Type;

    private PlayerType player2Type;

    private PlayerNumber nextMove;

    private GameState state;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<List<String>> rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public PlayerType getPlayer1Type() {
        return player1Type;
    }

    public void setPlayer1Type(PlayerType player1Type) {
        this.player1Type = player1Type;
    }

    public PlayerType getPlayer2Type() {
        return player2Type;
    }

    public void setPlayer2Type(PlayerType player2Type) {
        this.player2Type = player2Type;
    }

    public PlayerNumber getNextMove() {
        return nextMove;
    }

    public void setNextMove(PlayerNumber nextMove) {
        this.nextMove = nextMove;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
