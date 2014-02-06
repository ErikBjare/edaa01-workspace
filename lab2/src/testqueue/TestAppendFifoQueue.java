package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;

public class TestAppendFifoQueue {
    FifoQueue<Integer> oneIntQueue;
    FifoQueue<Integer> twoIntQueue;

    @Before
    public void setUp() throws Exception {
        oneIntQueue = new FifoQueue<Integer>();
        twoIntQueue = new FifoQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        oneIntQueue = null;
        twoIntQueue = null;
    }

    @Test
    public final void testAppend() {
        oneIntQueue.offer(1);
        oneIntQueue.offer(2);
        twoIntQueue.offer(3);
        twoIntQueue.offer(4);
        oneIntQueue.append(twoIntQueue);
        assertTrue(oneIntQueue.size() == 4);
        assertTrue(twoIntQueue.size() == 0);
    }

    @Test
    public final void testAppendToEmpty() {
        twoIntQueue.offer(1);
        twoIntQueue.offer(2);
        twoIntQueue.offer(3);
        twoIntQueue.offer(4);
        oneIntQueue.append(twoIntQueue);
        assertTrue(oneIntQueue.size() == 4);
        assertTrue(twoIntQueue.size() == 0);
    }

    @Test
    public final void testAppendEmpty() {
        oneIntQueue.offer(2);
        oneIntQueue.append(twoIntQueue);
        assertTrue(oneIntQueue.size() == 1);
        assertTrue(twoIntQueue.size() == 0);
    }

    @Test
    public final void testAppendEmptyToEmpty() {
        oneIntQueue.append(twoIntQueue);
        assertTrue(oneIntQueue.size() == 0);
        assertTrue(twoIntQueue.size() == 0);
    }

}