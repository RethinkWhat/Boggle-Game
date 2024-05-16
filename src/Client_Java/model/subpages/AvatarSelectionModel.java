package Client_Java.model.subpages;

import Client_Java.model.BoggleApp.BoggleClient;

public class AvatarSelectionModel {
    /**
     * The boggle client.
     */
    private BoggleClient wfImpl;
    /**
     * The username.
     */
    private String username;

    /**
     * Constructs a model with a specified username and boggle client.
     * @param username Specified username.
     * @param wfImpl Specified boggle client.
     */
    public AvatarSelectionModel(String username, BoggleClient wfImpl) {
        this.wfImpl = wfImpl;
        this.username = username;
    }

    /**
     * Updates the profile picture of the user.
     * @param username
     * @param selectedAvatar
     */
    public void editAvatar(String username, String selectedAvatar) {
        try {
            wfImpl.editInfo(username, "pfp", selectedAvatar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }
}
