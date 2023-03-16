package com.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sequence")

public class dataSeq {
	
	  @Id
	    private String id;

	    
	    private long seq;


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public long getSeq() {
			return seq;
		}


		public void setSeq(long seq) {
			this.seq = seq;
		}


		public dataSeq(String id, long seq) {
			super();
			this.id = id;
			this.seq = seq;
		}


		public dataSeq() {
			super();

		}


		@Override
		public String toString() {
			return "dataSeq [id=" + id + ", seq=" + seq + "]";
		}
	    
	    
	    

	}

	  