package io.github.mosps.model.party;

import io.github.mosps.model.party.setting.PartyRolePreset;

import java.util.HashSet;
import java.util.Set;

public class Party {
    private final long guildId;
    private final long ownerId;
    private final long partyId;
    private final long createdTime = System.currentTimeMillis();

    private Set<Long> members = new HashSet<>();

    private String messageId;

    private String destination;
    private String time;
    private String note;

    private PartyRolePreset preset = PartyRolePreset.NORMAL_5;

    private boolean closed = false;

    public Party(long guildId, long partyId, long ownerId) {
        this.guildId = guildId;
        this.partyId = partyId;
        this.ownerId = ownerId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public Set<Long> getMembers() {
        return members;
    }

    public void addMembers(long memberId) {
        members.add(memberId);
    }

    public void removeMembers(long memberId) {
        members.remove(memberId);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PartyRolePreset getPreset() {
        return preset;
    }

    public void setPreset(PartyRolePreset preset) {
        this.preset = preset;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public long getGuildId() {
        return guildId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public long getPartyId() {
        return partyId;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
    }

    public void open() {
        closed = false;
    }
}