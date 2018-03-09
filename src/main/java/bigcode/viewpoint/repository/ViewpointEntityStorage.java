package bigcode.viewpoint.repository;

import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import bigcode.viewpoint.model.ViewpointEntity;
import bigcode.viewpoint.model.ViewpointModel;

public interface ViewpointEntityStorage<VM extends ViewpointModel, VE extends ViewpointEntity> {

	
	CompletionStage<VE> createNew(VM model);
	
	CompletionStage<VE> findById(UUID id);
	
	Stream<VE> findAll(VM model);
}
