package Client_Java.controller.subpages;

import Client_Java.controller.ClientApplicationController;
import Client_Java.model.subpages.HowToPlayModel;
import Client_Java.view.subpages.HowToPlayView;
import shared.SwingResources;

/**
 * The HowToPlayController processes going back to the home page and joining a game.
 */
public class HowToPlayController {
    /**
     * The view.
     */
    private HowToPlayView view;
    /**
     * The model.
     */
    private HowToPlayModel model;
    /**
     * The parent controller.
     */
    private ClientApplicationController parent;

    /**
     * Constructs a HowToPlayController with a specified view and model.
     * @param view The specified view.
     * @param model The specified model.
     */
    public HowToPlayController(HowToPlayView view, HowToPlayModel model, ClientApplicationController parent) {
        this.view = view;
        this.model = model;
        this.parent = parent;

        // action listeners
        view.getBtnJoinGame().addActionListener(new HomeController.JoinGameListener(parent));
        view.getBtnBack().addActionListener(e -> parent.getView().showHome());

        // mouse listeners
        view.getBtnJoinGame().addMouseListener(new SwingResources.CursorChanger(view.getBtnJoinGame()));
        view.getBtnBack().addMouseListener(new SwingResources.CursorChanger(view.getBtnBack()));
    }
}
