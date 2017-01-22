package org.mfaruga.MFSpringWeb;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/restAsync")
public class AsynchronousController {

	@RequestMapping(path = "/xml/{id}", method = RequestMethod.GET, produces = "application/xml")
	public Callable<CreateTaskResponse> createNewTaskXMLAsyncCallable(final @PathVariable("id") Long id) {
		return new Callable<CreateTaskResponse>() {
			@Override
			public CreateTaskResponse call() throws Exception {
				Task task = new Task();
				task.setName(id.toString());
				task.setComments("Some comments");
				Thread.sleep(2000);
				return new CreateTaskResponse(task);
			}
		};
	}

	@RequestMapping(path = "/json/{id}", method = RequestMethod.GET, produces = "application/json")
	public DeferredResult<CreateTaskResponse> createNewTaskJSONDeffered(final @PathVariable("id") Long id) {
		
		final DeferredResult<CreateTaskResponse> defResult = new DeferredResult<>();
		CompletableFuture.runAsync(new Runnable() {			
			@Override
			public void run() {
				Task task = new Task();
				task.setName(id.toString());
				task.setComments("Some comments");
				defResult.setResult(new CreateTaskResponse(task));			
			}
		});

		return defResult;
		
	}
	
	
}
