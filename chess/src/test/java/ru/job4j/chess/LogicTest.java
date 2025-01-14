package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    void whenMoveNotDiagonalThenThrowImpossibleMoveException() {
        Logic logic = new Logic();
        Figure bishopBlack = new BishopBlack(Cell.C1);
        logic.add(bishopBlack);

        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.C2))
                .isInstanceOf(ImpossibleMoveException.class);
    }

    @Test
    void whenCellOnTheWayIsOccupiedThenThrowOccupiedCellException() {
        Logic logic = new Logic();
        Figure bishopBlack = new BishopBlack(Cell.C1);
        Figure pawn = new PawnBlack(Cell.D2);
        logic.add(bishopBlack);
        logic.add(pawn);

        assertThatThrownBy(() -> logic.move(Cell.C1, Cell.G5))
                .isInstanceOf(OccupiedCellException.class);
    }
}