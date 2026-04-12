package io.github.mosps.actions;

import io.github.mosps.actions.party.*;
import io.github.mosps.actions.party.setting.*;
import io.github.mosps.actions.profile.*;
import io.github.mosps.actions.profile.imagine.*;

import java.util.HashMap;
import java.util.Map;

public class ActionManager {

    private static final Map<String, Action> actions = new HashMap<>();

    static {
        register("party:join", new PartyJoinAction());
        register("party:leave", new PartyLeaveAction());
        register("party:toggle", new PartyToggleAction());
        register("party:create", new PartyCreateAction());
        register("party:create_confirm", new PartyCreateConfirmAction());
        register("party:info", new PartyInfoAction());
        register("party:setting", new PartySettingAction());
        register("party:edit", new PartyEditAction());
        register("party:edit_confirm", new PartyEditConfirmAction());
        register("party:role", new PartyRoleSettingAction());
        register("party:delete", new PartyDeleteAction());
        register("party:delete_accept", new PartyDeleteAcceptAction());
        register("party:delete_deny", new PartyDeleteDenyAction());
        register("party:admin", new PartyAdminAction());

        register("profile:create", new ProfileCreateAction());
        register("profile:register", new ProfileRegisterAction());
        register("profile:next", new ProfilePageNextAction());
        register("profile:prev", new ProfilePagePrevAction());
        register("profile:imagine_create", new ImagineCreateAction());
        register("profile:imagine_edit", new ImagineEditAction());
        register("profile:imagine_confirm", new ImagineConfirmAction());
        register("profile:imagine_add_next", new ImagineAddPageNextAction());
        register("profile:imagine_add_prev", new ImagineAddPagePrevAction());
        register("profile:imagine_remove_next", new ImagineRemovePageNextAction());
        register("profile:imagine_remove_prev", new ImagineRemovePagePrevAction());
        register("profile:admin", new ProfileAdminAction());
    }

    public static void register(String key, Action action) {
        actions.put(key, action);
    }

    public static Action get(String key) {
        Action action = actions.get(key);
        if (action == null) {
            throw new IllegalArgumentException(key + " is unknown key");
        }
        return actions.get(key);
    }
}
