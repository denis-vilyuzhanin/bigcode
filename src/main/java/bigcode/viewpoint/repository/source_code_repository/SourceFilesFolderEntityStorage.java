package bigcode.viewpoint.repository.source_code_repository;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import bigcode.viewpoint.model.source_code_repository.SourceCodeRepositoryModel;
import bigcode.viewpoint.model.source_code_repository.SourceFilesFolder;
import bigcode.viewpoint.repository.ViewpointEntityStorage;

public interface SourceFilesFolderEntityStorage 
	extends ViewpointEntityStorage<SourceCodeRepositoryModel, SourceFilesFolder> {

	default CompletionStage<SourceFilesFolder> createNewRoot(SourceCodeRepositoryModel model) {
		return createNew(model);
	}

	Stream<SourceFilesFolder> findAllRoots();
	
	CompletionStage<SourceFilesFolder> createSubFolder(SourceFilesFolder parent);
}
