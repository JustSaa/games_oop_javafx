package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class BishopBlackTest {

    @Test
    void position() {
        Cell position = Cell.A2;
        BishopBlack bishopBlack = new BishopBlack(position);
        assertThat(bishopBlack.position()).isEqualTo(position);
    }

    @Test
    void copy() {
        Cell originalPosition = Cell.F8;
        Figure bishopBlack = new BishopBlack(originalPosition);
        Figure bishopBlackCopy = bishopBlack.copy(originalPosition);

        assertThat(bishopBlackCopy).isNotSameAs(bishopBlack);
        assertThat(bishopBlackCopy.position()).isEqualTo(bishopBlack.position());
    }

    @Test
    void way() {
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Cell[] expectedPath = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] result = bishopBlack.way(Cell.G5);

        assertThat(result).isEqualTo(expectedPath);
    }

    @Test
    void whenWayNotDiagonalThenException() {
        Figure bishopBlack = new BishopBlack(Cell.C1);

        assertThatThrownBy(() -> bishopBlack.way(Cell.C2))
                .isInstanceOf(ImpossibleMoveException.class);
    }
}