package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;
  private final HelloTraceV1 traceV1;

  public void orderItem(String itemId) {
    TraceStatus status = null;

    try {
      status = traceV1.begin("OrderService.orderItem()");
      orderRepository.save(itemId);
      traceV1.end(status);
    } catch (Exception e) {
      traceV1.exception(status, e);
      throw e;
    }
  }

}
