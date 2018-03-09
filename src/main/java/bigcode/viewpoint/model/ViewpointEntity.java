package bigcode.viewpoint.model;

import java.util.UUID;

public abstract class ViewpointEntity {

	private UUID id;
	private UUID modelId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getModelId() {
		return modelId;
	}

	public void setModelId(UUID modelId) {
		this.modelId = modelId;
	}
		
	
}
