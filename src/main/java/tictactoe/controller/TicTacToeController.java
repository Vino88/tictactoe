package tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.service.GameService;
import tictactoe.entity.Game;
import tictactoe.entity.Game.PlayerType;
import tictactoe.entity.AppUser;
import tictactoe.repository.AppUserRepository;

import java.security.Principal;

@Controller
public class TicTacToeController {

    private final GameService gameService;

    private final AppUserRepository appUserRepository;

    @Autowired
    public TicTacToeController(GameService gameService, AppUserRepository appUserRepository) {
        this.gameService = gameService;
        this.appUserRepository = appUserRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal, Model model) {
        AppUser appUser = getAppUser(principal);

        Game game = gameService.getLastGame(appUser);
        if (game == null) {
            game = gameService.create(appUser, true, 3);
        }

        setModelGameAttributes(model, game);

        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String takeTurns(
            Model model,
            Principal principal,
            @RequestParam(value = "tile_id", required = false) String tileId,
            @RequestParam(value = "new_game", required = false, defaultValue = "false") boolean newGame,
            @RequestParam(value = "board_size", required = false, defaultValue = "0") int boardSize
    ) {
        try {
            AppUser appUser = getAppUser(principal);

            Game game;
            if (newGame) {
                game = gameService.create(appUser, true, boardSize);
            } else {
                game = gameService.getLastGame(appUser);
                gameService.takeTurn(game, tileId); // Player Turn
                gameService.takeTurnRandom(game); // Computer Turn
            }

            setModelGameAttributes(model, game);

            return "index";
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setModelGameAttributes(Model model, Game game) {
        boolean playerGoFirst = game.getPlayer1Type() == PlayerType.HUMAN;

        String playerStatus;
        switch (game.getState()) {
            case PLAYER_1_WIN:
                playerStatus = playerGoFirst ? "WON" : "LOST";
                break;
            case PLAYER_2_WIN:
                playerStatus = playerGoFirst ? "LOST" : "WON";
                break;
            case DRAW:
                playerStatus = "DRAW";
                break;
            case IN_PROGRESS:
            default:
                playerStatus = "IN_PROGRESS";
                break;
        }

        model.addAttribute("playerGoFirst", playerGoFirst);
        model.addAttribute("playStatus", playerStatus);
        model.addAttribute("board", game.getRows());
        model.addAttribute("boardSize", game.getBoardSize());
    }

    private AppUser getAppUser(Principal principal) {
        AppUser appUser = appUserRepository.findByUsername(principal.getName());
        if (appUser == null) {
            throw new UsernameNotFoundException("Invalid username: " + principal.getName());
        }
        return appUser;
    }
}
