package my.unittest.com;

public class CircularBuffer {
	public int in_index;
	public int out_index;
	public int length; 
	public int []buffer;
	
	public CircularBuffer(int length){
		in_index = 0;
		out_index = 0;
		this.length = length;
		buffer = new int[length];
	}
	
	public boolean IsEmpty(){
		return in_index == out_index;
	}
	
	public void Put(int value){
		if(!isInIndexLeftOneofOutIndex())
		{
			buffer[in_index] = value;
			InIndexPlusOne();
		}
	}
	
	public int Get()
	{
		int rtnIndex = out_index;
		if(!this.IsEmpty())
		{
			OutIndexPlusOne();
		}else
			return -1;
		
		return buffer[rtnIndex];
	}
	
	public void Clear()
	{
		in_index = 0;
		out_index = 0;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public boolean IsFull(){
		return isInIndexLeftOneofOutIndex();
	}
	
	private boolean isInIndexLeftOneofOutIndex(){
		if(in_index > out_index)
		{
			if(in_index == length-1 && out_index==0)
				return true;
			else
				return false;
		}else if(in_index == out_index - 1)
			return true;
		else
			return false;
	}
	
	private void InIndexPlusOne(){
		if(in_index <= this.length-2)
			in_index++;
		else if(in_index == this.length-1)
			in_index = 0;
	}

	private void OutIndexPlusOne(){
		if(out_index <= this.length-2)
			out_index++;
		else if(out_index == this.length-1)
			out_index = 0;
	}

	
}
