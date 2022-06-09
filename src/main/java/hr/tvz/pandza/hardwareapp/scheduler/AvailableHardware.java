package hr.tvz.pandza.hardwareapp.scheduler;

import hr.tvz.pandza.hardwareapp.dto.HardwareDTO;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import hr.tvz.pandza.hardwareapp.service.HardwareService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class AvailableHardware extends QuartzJobBean {

    @Autowired
    private HardwareRepository hardwareRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Hardware> hardwareList = hardwareRepository.findAll();

        System.out.println("-----------------------------------------------");
        System.out.println("Trenutno dostupni hardveri");
        hardwareList.forEach(h -> System.out.println(h.getName() + " - " + h.getQuantity()));
        System.out.println("-----------------------------------------------");
    }
}
