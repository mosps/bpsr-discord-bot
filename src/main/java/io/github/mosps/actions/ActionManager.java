package io.github.mosps.actions;

import io.github.mosps.actions.party.*;
import io.github.mosps.actions.party.setting.*;
import io.github.mosps.actions.party.*;
import io.github.mosps.actions.party.setting.*;
import io.github.mosps.actions.profile.ProfileAdminAction;
import io.github.mosps.actions.profile.ProfileCreateAction;
import io.github.mosps.actions.profile.ProfileRegisterAction;
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
        register("profile:imagine_create", new ImagineCreateAction());
        register("profile:imagine_edit", new ImagineEditAction());
        register("profile:imagine_confirm", new ImagineConfirmAction());
        register("profile:imagine_next", new ImaginePageNextAction());
        register("profile:imagine_prev", new ImaginePagePrevAction());
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
