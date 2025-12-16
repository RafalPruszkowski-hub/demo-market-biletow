package alebilet.rekrutacja.market.events;

import alebilet.rekrutacja.market.events.api.commands.AddTicketCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("AddTicketCommand validation test")
class AddTicketCommandTest {

    @Nested
    @DisplayName("Price validation")
    class PriceValidation {

        @Test
        @DisplayName("Should throw exception when price is null")
        void shouldThrowException_whenPriceIsNull() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    null,
                    new BigDecimal("119.99"),
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Price must be greater than 0");
        }

        @Test
        @DisplayName("Should throw exception when price is zero")
        void shouldThrowException_whenPriceIsZero() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    BigDecimal.ZERO,
                    new BigDecimal("119.99"),
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Price must be greater than 0");
        }

        @Test
        @DisplayName("Should throw exception when price is negative")
        void shouldThrowException_whenPriceIsNegative() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("-10.00"),
                    new BigDecimal("119.99"),
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Price must be greater than 0");
        }

        @Test
        @DisplayName("Should create command with very small price")
        void shouldCreateCommand_withVerySmallPrice() {
            // when
            AddTicketCommand command = new AddTicketCommand(
                    new BigDecimal("0.01"),
                    new BigDecimal("0.02"),
                    "TKT-001",
                    TicketCategory.STANDARD
            );

            // then
            assertThat(command).isNotNull();
            assertThat(command.price()).isEqualByComparingTo(new BigDecimal("0.01"));
        }

        @Test
        @DisplayName("Should create command with very large price")
        void shouldCreateCommand_withVeryLargePrice() {
            // when
            AddTicketCommand command = new AddTicketCommand(
                    new BigDecimal("99999.99"),
                    new BigDecimal("99999.99"),
                    "TKT-001",
                    TicketCategory.PREMIUM
            );

            // then
            assertThat(command).isNotNull();
            assertThat(command.price()).isEqualByComparingTo(new BigDecimal("99999.99"));
        }

        @Test
        @DisplayName("Should create command when price equals original price")
        void shouldCreateCommand_whenPriceEqualsOriginalPrice() {
            // given
            BigDecimal price = new BigDecimal("99.99");
            BigDecimal originalPrice = new BigDecimal("99.99");

            // when
            AddTicketCommand command = new AddTicketCommand(price, originalPrice, "TKT-001", TicketCategory.VIP);

            // then
            assertThat(command).isNotNull();
            assertThat(command.price()).isEqualByComparingTo(originalPrice);
        }
    }

    @Nested
    @DisplayName("Original price validation")
    class OriginalPriceValidation {

        @Test
        @DisplayName("Should throw exception when original price is null")
        void shouldThrowException_whenOriginalPriceIsNull() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    null,
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Original price must be greater than 0");
        }

        @Test
        @DisplayName("Should throw exception when original price is zero")
        void shouldThrowException_whenOriginalPriceIsZero() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    BigDecimal.ZERO,
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Original price must be greater than 0");
        }

        @Test
        @DisplayName("Should throw exception when original price is negative")
        void shouldThrowException_whenOriginalPriceIsNegative() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("-10.00"),
                    "TKT-001",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Original price must be greater than 0");
        }
    }

    @Nested
    @DisplayName("Ticket number validation")
    class TicketNumberValidation {

        @Test
        @DisplayName("Should throw exception when ticket number is null")
        void shouldThrowException_whenTicketNumberIsNull() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("119.99"),
                    null,
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Ticket number cannot be empty");
        }

        @Test
        @DisplayName("Should throw exception when ticket number is empty")
        void shouldThrowException_whenTicketNumberIsEmpty() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("119.99"),
                    "",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Ticket number cannot be empty");
        }

        @Test
        @DisplayName("Should throw exception when ticket number is blank")
        void shouldThrowException_whenTicketNumberIsBlank() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("119.99"),
                    "   ",
                    TicketCategory.STANDARD
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Ticket number cannot be empty");
        }

        @Test
        @DisplayName("Should create command with long ticket number")
        void shouldCreateCommand_withLongTicketNumber() {
            // given
            String longTicketNumber = "TKT-VERY-LONG-TICKET-NUMBER-123456789";

            // when
            AddTicketCommand command = new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("119.99"),
                    longTicketNumber,
                    TicketCategory.VIP
            );

            // then
            assertThat(command.ticketNumber()).isEqualTo(longTicketNumber);
        }
    }

    @Nested
    @DisplayName("Category validation")
    class CategoryValidation {

        @Test
        @DisplayName("Should throw exception when category is null")
        void shouldThrowException_whenCategoryIsNull() {
            // when & then
            assertThatThrownBy(() -> new AddTicketCommand(
                    new BigDecimal("99.99"),
                    new BigDecimal("119.99"),
                    "TKT-001",
                    null
            ))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Category cannot be null");
        }

        @Test
        @DisplayName("Should create command with all categories")
        void shouldCreateCommand_withAllCategories() {
            // given
            BigDecimal price = new BigDecimal("99.99");
            BigDecimal originalPrice = new BigDecimal("119.99");
            String ticketNumber = "TKT-001";

            // when & then
            assertThat(new AddTicketCommand(price, originalPrice, ticketNumber, TicketCategory.STANDARD))
                    .isNotNull();
            assertThat(new AddTicketCommand(price, originalPrice, ticketNumber, TicketCategory.VIP))
                    .isNotNull();
            assertThat(new AddTicketCommand(price, originalPrice, ticketNumber, TicketCategory.PREMIUM))
                    .isNotNull();
        }
    }

    @Nested
    @DisplayName("Valid command creation")
    class ValidCommandCreation {

        @Test
        @DisplayName("Should create command when all fields are valid")
        void shouldCreateCommand_whenAllFieldsAreValid() {
            // given
            BigDecimal price = new BigDecimal("99.99");
            BigDecimal originalPrice = new BigDecimal("119.99");
            String ticketNumber = "TKT-001";
            TicketCategory category = TicketCategory.STANDARD;

            // when
            AddTicketCommand command = new AddTicketCommand(price, originalPrice, ticketNumber, category);

            // then
            assertThat(command).isNotNull();
            assertThat(command.price()).isEqualByComparingTo(price);
            assertThat(command.originalPrice()).isEqualByComparingTo(originalPrice);
            assertThat(command.ticketNumber()).isEqualTo(ticketNumber);
            assertThat(command.category()).isEqualTo(category);
        }
    }
}
