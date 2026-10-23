package hu.bme.mit.spaceship;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }


@Test
    void constructor_SetsTorpedoCount() {
        // Arrange + Act
        TorpedoStore store = new TorpedoStore(5);
 
        // Assert
        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }
 
    @Test
    void fire_Success_DecreasesTorpedoCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5, 0.0);
 
        // Act
        boolean result = store.fire(2);
 
        // Assert
        assertTrue(result);
        assertEquals(3, store.getTorpedoCount());
    }
 
    @Test
    void fire_LastTorpedo_StoreBecomesEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);
 
        // Act
        store.fire(1);
 
        // Assert
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }
 
    @Test
    void fire_Failure_CountUnchanged() {
        // Arrange: failure rate 1.0 means firing always fails
        TorpedoStore store = new TorpedoStore(5, 1.0);
 
        // Act
        boolean result = store.fire(1);
 
        // Assert
        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }
 
    @Test
    void fire_MoreThanAvailable_Throws() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);
 
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }
 
    @Test
    void fire_ZeroTorpedoes_Throws() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);
 
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }
 
    @Test
    void fire_EmptyStore_Throws() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);
 
        // Act + Assert
        assertTrue(store.isEmpty());
        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }
}
 