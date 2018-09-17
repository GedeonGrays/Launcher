package com.mojang.authlib.yggdrasil;

import java.util.UUID;

import ru.gravit.launcher.Launcher;
import ru.gravit.launcher.LauncherAPI;
import ru.gravit.launcher.client.ClientLauncher;
import ru.gravit.launcher.helper.SecurityHelper;
import ru.gravit.launcher.profiles.PlayerProfile;

@SuppressWarnings("unused")
@LauncherAPI
public final class CompatProfile {
    public static final String SKIN_URL_PROPERTY = ClientLauncher.SKIN_URL_PROPERTY;
    public static final String SKIN_DIGEST_PROPERTY = ClientLauncher.SKIN_DIGEST_PROPERTY;
    public static final String CLOAK_URL_PROPERTY = ClientLauncher.CLOAK_URL_PROPERTY;
    public static final String CLOAK_DIGEST_PROPERTY = ClientLauncher.CLOAK_DIGEST_PROPERTY;

    public static CompatProfile fromPlayerProfile(PlayerProfile profile) {
        return profile == null ? null : new CompatProfile(profile.uuid, profile.username,
                profile.skin == null ? null : profile.skin.url,
                profile.skin == null ? null : SecurityHelper.toHex(profile.skin.digest),
                profile.cloak == null ? null : profile.cloak.url,
                profile.cloak == null ? null : SecurityHelper.toHex(profile.cloak.digest)
        );
    }
    // Instance
    public final UUID uuid;
    public final String uuidHash, username;
    public final String skinURL, skinDigest;

    public final String cloakURL, cloakDigest;

    public CompatProfile(UUID uuid, String username, String skinURL, String skinDigest, String cloakURL, String cloakDigest) {
        this.uuid = uuid;
        uuidHash = Launcher.toHash(uuid);
        this.username = username;
        this.skinURL = skinURL;
        this.skinDigest = skinDigest;
        this.cloakURL = cloakURL;
        this.cloakDigest = cloakDigest;
    }

    public int countProperties() {
        int count = 0;
        if (skinURL != null)
			count++;
        if (skinDigest != null)
			count++;
        if (cloakURL != null)
			count++;
        if (cloakDigest != null)
			count++;
        return count;
    }
}