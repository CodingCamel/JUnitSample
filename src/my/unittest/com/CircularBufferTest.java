package my.unittest.com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CircularBufferTest {

	private static CircularBuffer circularBuffer = new CircularBuffer(10);
	
	@Before
	public void setup() throws Exception{
		circularBuffer.Clear();
	}
	
	@Test
	public void empty_after_creation() {
		assertTrue(circularBuffer.IsEmpty());
	}
	
	@Test
	public void not_empty_after_put() {
		circularBuffer.Put(42);
		assertFalse(circularBuffer.IsEmpty());
	}
	
	@Test
	public void emply_after_remove_the_last_item(){
		circularBuffer.Put(42);
		assertFalse(circularBuffer.IsEmpty());
		circularBuffer.Get();
		assertTrue(circularBuffer.IsEmpty());
	}
	
	@Test 
	public void IsFull(){
		for(int i = 0; i< circularBuffer.getLength()-1; i++)
		{
			circularBuffer.Put(42);
		}
		assertTrue(circularBuffer.IsFull());
	}
	
	@Test 
	public void IsFullAfterPutMoreThanLength(){
		for(int i = 0; i< circularBuffer.getLength(); i++)
		{
			circularBuffer.Put(42);
		}
		assertTrue(circularBuffer.IsFull());
	}
	
	@Test 
	public void IsNotFull(){
		for(int i = 0; i< circularBuffer.getLength()-2; i++)
		{
			circularBuffer.Put(42);
		}
		assertFalse(circularBuffer.IsFull());
	}

	@Test 
	public void NotFullFromFull(){
		for(int i = 0; i< circularBuffer.getLength()-1; i++)
		{
			circularBuffer.Put(42);
		}
		circularBuffer.Get();
		assertFalse(circularBuffer.IsFull());
	}

	@Test 
	public void EmptyFromFull(){
		for(int i = 0; i< circularBuffer.getLength()-1; i++)
		{
			circularBuffer.Put(42);
		}
		assertTrue(circularBuffer.IsFull());
		for(int i = 0; i< circularBuffer.getLength()-1; i++)
		{
			circularBuffer.Get();
		}
		assertTrue(circularBuffer.IsEmpty());
	}

	@Test 
	public void EmptyFromFullAfterGetExceed(){
		for(int i = 0; i< circularBuffer.getLength()-1; i++)
		{
			circularBuffer.Put(42);
		}
		assertTrue(circularBuffer.IsFull());
		for(int i = 0; i< circularBuffer.getLength(); i++)
		{
			circularBuffer.Get();
		}
		assertTrue(circularBuffer.IsEmpty());
	}
	
	@Test 
	public void PutGet_FIFO(){
		circularBuffer.Put(42);
		assertEquals(42, circularBuffer.Get());
	}

	@Test 
	public void PutGet_FIFO_3TimesLengths(){
		for(int i = 0; i< circularBuffer.getLength()*3; i++)
		{
			circularBuffer.Put(42);
			assertEquals(42, circularBuffer.Get());
		}
	}
	
	@Test 
	public void PutGet_FIFO_5Number(){
		if(circularBuffer.getLength()>5)
		{
			circularBuffer.Put(42);
			circularBuffer.Put(1);
			circularBuffer.Put(56);
			circularBuffer.Put(72);
			circularBuffer.Put(82);
			assertEquals(42, circularBuffer.Get());
			assertEquals(1, circularBuffer.Get());
			assertEquals(56, circularBuffer.Get());
			assertEquals(72, circularBuffer.Get());
			assertEquals(82, circularBuffer.Get());
		}
	}

	@Test 
	public void GetFromEmpty(){
		assertEquals(-1, circularBuffer.Get());
	}

}
