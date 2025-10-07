package org.gamer6894;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
    default String greeting()
    {
        return "Hello";
    }



@ConfigItem(
        keyName = "caughtCount",
        name = "My Persistent Counter",
        description = "An example of a variable that saves programmatically.",
        hidden = true // Hide this from the settings panel
)
default int caughtCount() {
    return 0;
    }



@ConfigItem(
        keyName = "xpGained",
        name = "My Persistent Counter",
        description = "An example of a variable that saves programmatically.",
        hidden = true // Hide this from the settings panel
)
default int xpGained() {
    return 0;
}



@ConfigItem(
        keyName = "matureProgress",
        name = "My Persistent Counter",
        description = "An example of a variable that saves programmatically.",
        hidden = true // Hide this from the settings panel
)
default int matureProgress() {
    return 0;
}



@ConfigItem(
        keyName = "matureSuccess",
        name = "My Persistent Counter",
        description = "An example of a variable that saves programmatically.",
        hidden = true // Hide this from the settings panel
)
default int matureSuccess() {
    return 0;
}
}