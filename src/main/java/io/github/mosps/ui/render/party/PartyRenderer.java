
package io.github.mosps.ui.render.party;

import io.github.mosps.model.data.Classes;
import io.github.mosps.model.data.Imagines;
import io.github.mosps.model.data.Role;
import io.github.mosps.model.profile.Profile;
import io.github.mosps.model.profile.ProfileManager;
import io.github.mosps.ui.render.BaseRenderer;
import io.github.mosps.ui.render.RenderResult;
import io.github.mosps.ui.views.party.PartyView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartyRenderer extends BaseRenderer<PartyView> {

    @Override
    public RenderResult render(PartyView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);
        addMemberListField(view, embedBuilder);

        List<Button> buttons = createPartyAccessButton(view);
        buttons = disableIf(view.closed, buttons);
        buttons.add(createPartySettingButton(view));

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), ActionRow.of(buttons));
    }

    private EmbedBuilder buildEmbed(PartyView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setColor(getColor(!view.closed));

        embedBuilder.setTitle("パーティ募集");

        embedBuilder.setDescription(
                """
                ID: `%s`
                -----------------------------------
                **目的地:** %s
                **時間:** %s
                **備考:** %s
                -----------------------------------
                """.formatted(view.partyId, view.destination, view.time, view.note)
        );

        return embedBuilder;
    }

    private void addMemberListField(PartyView view, EmbedBuilder embedBuilder) {
        embedBuilder.addField(
                buildMemberCap(view),
                buildMembersString(view),
                false
        );
    }

    private String buildMemberCap(PartyView view) {
        if (view.role.containsKey(Role.ALL)) {
            return "参加者 `%s/%s`".formatted(view.members.size(), view.role.get(Role.ALL));
        }

        Map<Role, Long> counts = view.members.stream()
                .map(ProfileManager::getProfile)
                .map(Profile::getMainClass)
                .collect(Collectors.groupingBy(
                        Classes::getRole,
                        Collectors.counting()
                ));

        Map<Role, Integer> limits = view.role;

        StringBuilder stringBuilder = new StringBuilder().append("参加者 ");
        for (Role role: Role.values()) {
            if (role == Role.ALL) continue;

            long current = counts.getOrDefault(role, 0L);
            int max = limits.getOrDefault(role, 0);

            stringBuilder.append(role.getName()).append(":`")
                    .append(current).append("/").append(max).append("` ");
        }

        return stringBuilder.append("\n").toString();
    }

    private String buildMembersString(PartyView view) {
        StringBuilder stringBuilder = new StringBuilder();
        view.members.forEach(id -> {
                    Profile profile = ProfileManager.getProfile(id);
                    stringBuilder.append("<@").append(id).append("> ")
                            .append(profile.getMainClass().getEmoji()).append(profile.getMainClass().getStyle()).append(" ")
                            .append(getEquippedImaginesView(profile.getEquippedImagines())).append(" ")
                            .append("\n");
                }
        );

        return stringBuilder.isEmpty() ? " " : stringBuilder.toString();
    }

    private List<Button> createPartyAccessButton(PartyView view) {
        List<Button> buttons = new ArrayList<>();

        buttons.add(Button.success("party:join:" + view.partyId, "🟢参加"));
        buttons.add(Button.danger("party:leave:" + view.partyId, "🔴退出"));

        return buttons;
    }

    private Button createPartySettingButton(PartyView view) {
        return Button.secondary("party:setting:" + view.partyId, "⚙");
    }

    private String getEquippedImaginesView(Map<Imagines, String> equippedImagines) {
       return equippedImagines.isEmpty()
                ? ""
                : equippedImagines.entrySet().stream()
                .map(entry -> entry.getKey().getDisplay() + entry.getValue())
                .collect(Collectors.joining(""));
    }

    private List<Button> disableIf(boolean condition, List<Button> buttons) {
        if (!condition) return buttons;

        return buttons.stream()
                .map(Button::asDisabled)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Color getColor(boolean condition) {
        return condition ? Color.GREEN : Color.RED;
    }
}
