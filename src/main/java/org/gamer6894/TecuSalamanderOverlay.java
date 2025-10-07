package org.gamer6894;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class TecuSalamanderOverlay extends Overlay {
    private final TecuSalamanderCounter plugin;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public TecuSalamanderOverlay(TecuSalamanderCounter plugin) {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Successful Catches:")
                .right(String.valueOf(plugin.getSuccessfulCatches()))
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("XP gained:")
                .right(String.valueOf(plugin.xpProgress()))
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Mature Drop Progress:")
                .right("."+String.valueOf(plugin.caughtVsDropRate())+"%")
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Mature Caught:")
                .right(String.valueOf(plugin.matureCaughtCount()))
                .build());
        return panelComponent.render(graphics);
    }
}