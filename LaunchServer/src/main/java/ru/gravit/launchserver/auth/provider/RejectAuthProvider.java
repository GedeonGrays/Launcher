package ru.gravit.launchserver.auth.provider;

import ru.gravit.launcher.helper.VerifyHelper;
import ru.gravit.launcher.serialize.config.entry.BlockConfigEntry;
import ru.gravit.launcher.serialize.config.entry.StringConfigEntry;
import ru.gravit.launchserver.auth.AuthException;

public final class RejectAuthProvider extends AuthProvider {
    private final String message;

    public RejectAuthProvider(BlockConfigEntry block) {
        super(block);
        message = VerifyHelper.verify(block.getEntryValue("message", StringConfigEntry.class), VerifyHelper.NOT_EMPTY,
                "Auth error message can't be empty");
    }

    @Override
    public AuthProviderResult auth(String login, String password, String ip) throws AuthException {
        return authError(message);
    }

    @Override
    public void close() {
        // Do nothing
    }
}