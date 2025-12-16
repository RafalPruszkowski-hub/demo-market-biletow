package alebilet.rekrutacja.market.events.api;

import alebilet.rekrutacja.market.events.api.commands.AddTicketCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventFacade eventFacade;

    @GetMapping("/list")
    public String getUpcomingEvents(Model model) {
        var events = eventFacade.getUpcomingEvents();
        model.addAttribute("events", events);
        return "events/list";
    }

    @GetMapping("/details/{publicEventId}")
    public String getEventDetails(@PathVariable UUID publicEventId, Model model) {
        var event = eventFacade.getEventDetails(publicEventId);
        model.addAttribute("event", event);
        return "/events/details";
    }

    @PostMapping("/{publicEventId}/tickets")
    public String addTicket(@PathVariable UUID publicEventId, @ModelAttribute AddTicketCommand command) {
        eventFacade.addTicket(publicEventId, command);
        return "redirect:/events/details/" + publicEventId;
    }
}
