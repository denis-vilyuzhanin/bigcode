package bigcode.viewpoint.repository;

import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import bigcode.viewpoint.model.ViewpointModel;

public interface ViewpointModelStorage<VM extends ViewpointModel> {

	CompletionStage<VM> createNew();
	
	CompletionStage<VM> findLatest();
	
	CompletionStage<VM> findById(UUID id);
	
	Stream<VM> findAll();
}
