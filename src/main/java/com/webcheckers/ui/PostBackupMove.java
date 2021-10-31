

package com.webcheckers.ui;
import com.google.gson.Gson;
import com.webcheckers.appl.GameManager;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.List;
import java.util.Objects;

import static spark.Spark.halt;

public class PostBackupMove implements Route{
    static final String VIEW_NAME = "game.ftl";

    private final GameManager gameManager;
    private final Gson gson = new Gson();

    public PostBackupMove(final GameManager manager){
        gameManager = manager;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();
        String message = "";
        Player user = session.attribute(("currentUser"));
        BoardView board = user.getGame().getBoard();
        List<Move> moves = board.getMoves();
        int length = moves.size();
        if(length == 0){
            message = "A move has not been made so no undo move will be made";
            return gson.toJson(Message.error(message));
        }
        else{
            Move move = moves.remove(length - 1);
            if(user.getGame().getActiveColor() == Piece.Color.RED) {
                board.reverse_piece(move, true);
            }else{
                board.reverse_piece(move, false);
            }
            message = "piece has successfully backed up";
            return gson.toJson(Message.info(message));
        }
        //return gson.toJson(Message.error(message));
    }
}
