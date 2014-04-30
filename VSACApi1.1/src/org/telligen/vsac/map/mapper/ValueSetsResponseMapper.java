package org.telligen.vsac.map.mapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.telligen.vsac.object.ValueSetsResponse;
import org.xml.sax.InputSource;


public class ValueSetsResponseMapper {

	private String singleFilePath = "SingleValueSetMapping.xml";
	private String multiFilePath = "MultiValueSetMapping.xml";
	private Mapping mapping = new Mapping();
	private boolean multiRetrieval;
	private File file;
	
	public ValueSetsResponseMapper() {
		
	}
	
	public ValueSetsResponseMapper(File file, boolean multiRetrieval) {
		this.file = file;
		this.multiRetrieval = multiRetrieval;
	}
	
	public ValueSetsResponse getValueSetsResponseObject() {
		try {
			if (multiRetrieval) {
				mapping.loadMapping(new InputSource(this.getClass().getClassLoader().getResourceAsStream(multiFilePath)));
			}
			else {
				mapping.loadMapping(new InputSource(this.getClass().getClassLoader().getResourceAsStream(singleFilePath)));
			}
			Unmarshaller unmar = new Unmarshaller(mapping);
			return (ValueSetsResponse) unmar.unmarshal(new InputSource(new FileReader(file)));
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
