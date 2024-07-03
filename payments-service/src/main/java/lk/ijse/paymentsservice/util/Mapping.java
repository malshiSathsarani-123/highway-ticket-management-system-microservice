package lk.ijse.paymentsservice.util;

import lk.ijse.paymentsservice.dto.PaymentDTO;
import lk.ijse.paymentsservice.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapping {

    final private ModelMapper mapper;

    public PaymentDTO toPaymentDTO(PaymentEntity paymentEntity) {
        return  mapper.map(paymentEntity, PaymentDTO.class);
    }
    public PaymentEntity toPayment(PaymentDTO paymentDTO) {
        return  mapper.map(paymentDTO, PaymentEntity.class);
    }
    public List<PaymentDTO> toPaymentDTOList(List<PaymentEntity> paymentEntities) {
        return mapper.map(paymentEntities, List.class);
    }

}
