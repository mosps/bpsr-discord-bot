
package io.github.mosps.render.party;

import io.github.mosps.data.Imagines;
import io.github.mosps.profile.Profile;
import io.github.mosps.profile.ProfileManager;
import io.github.mosps.render.BaseRenderer;
import io.github.mosps.render.RenderResult;
import io.github.mosps.views.party.PartyView;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageEditData;

import java.util.List;

public class PartyRenderer extends BaseRenderer<PartyView> {

    @Override
    public RenderResult render(PartyView view) {
        EmbedBuilder embedBuilder = buildEmbed(view);
        addMemberListField(view, embedBuilder);

        List<Button> buttons = createPartyAccessButton(view);
        buttons = disableIf(view.closed, buttons);

        return build(MessageEditData.fromEmbeds(embedBuilder.build()), ActionRow.of(buttons));
    }

    private EmbedBuilder buildEmbed(PartyView view) {
        EmbedBuilder embedBuilder = baseEmbed();

        embedBuilder.setTitle("パーティ募集");

        embedBuilder.setDescription(
                """
                -----------------------------------
                **目的地:** %s
                **時間:** %s
                **備考:** %s
                -----------------------------------
                """.formatted(view.destination, view.time, view.note)
        );

        return embedBuilder;
    }

    private void addMemberListField(PartyView view, EmbedBuilder embedBuilder) {
        StringBuilder members = buildMembersString(view);

        embedBuilder.addField(
                """
                参加者```%s/%s```
                """.formatted(view.members.size(), view.maxMembers),
                members.isEmpty()
                        ? "　"
                        : members.toString(),
                false
        );
    }

    private StringBuilder buildMembersString(PartyView view) {
        StringBuilder stringBuilder = new StringBuilder();
        view.members.forEach(id -> {
                    Profile profile = ProfileManager.getProfile(id);
                    stringBuilder.append("<@").append(id).append("> ")
                            .append(profile.getMainClass().getEmoji()).append(" ")
                            .append(Imagines.AIRONA.getEmoji()).append(" ")
                            .append("\n");
                }
        );

        return stringBuilder;
    }

    private List<Button> createPartyAccessButton(PartyView view) {
        Button join = Button.success("party:join:" + view.partyId, "🟢参加");
        Button leave = Button.danger("party:leave:" + view.partyId, "🔴退出");
        Button setting = Button.secondary("party:modal:" + view.partyId, "⚙");

        return List.of(join, leave, setting);
    }

    private List<Button> disableIf(boolean condition, List<Button> buttons) {
        if (!condition) return buttons;

        return buttons.stream()
                .map(Button::asDisabled)
                .toList();
    }
}
