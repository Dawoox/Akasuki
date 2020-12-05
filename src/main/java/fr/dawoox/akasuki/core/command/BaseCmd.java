package fr.dawoox.akasuki.core.command;

import discord4j.core.spec.EmbedCreateSpec;
import reactor.util.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Interface for executing commands
 * @author Dawoox
 * @version 1.0.0
 */
public abstract class BaseCmd {

    private final CommandCategory category;
    private final CommandPermission permission;
    private final List<String> names;
    @Nullable
    private final String alias;

    private boolean isEnabled;

    protected BaseCmd(CommandCategory category, CommandPermission permission, List<String> names, @Nullable String alias){
        this.category = category;
        this.permission = permission;
        this.names = new ArrayList<>(names);
        this.alias = alias;

        if (this.alias != null) { this.names.add(this.alias); }
    }

    protected BaseCmd(CommandCategory category, CommandPermission permission, List<String> names){
        this(category, permission, names, null);
    }

    protected BaseCmd(CommandCategory category, List<String> names, String alias) {
        this(category, CommandPermission.USER, names, alias);
    }

    protected BaseCmd(CommandCategory category, List<String> names) {
        this(category, names, null);
    }

    public abstract void execute(Context context);

    //public abstract Consumer<EmbedCreateSpec> getHelp(Context context);

    public CommandCategory getCategory() {
        return this.category;
    }

    public CommandPermission getPermission() {
        return this.permission;
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(this.names);
    }

    public String getName() {
        return this.names.get(0);
    }

    @Nullable
    public Optional<String> getAlias() {
        return Optional.ofNullable(this.alias);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
