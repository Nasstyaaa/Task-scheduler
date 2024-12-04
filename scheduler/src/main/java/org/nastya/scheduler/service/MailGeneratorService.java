package org.nastya.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.nastya.scheduler.dto.Message;
import org.nastya.scheduler.model.Task;
import org.nastya.scheduler.repository.TaskRepository;
import org.nastya.scheduler.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailGeneratorService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final KafkaPoducer kafkaPoducer;
    private final static String MESSAGE_HEADER = "Daily report";

    @Scheduled(cron = "0 0 0 * * ?")
    public void generateMessage() {
        userRepository.findAll().forEach(user -> {
            Message message;
            List<Task> userTasks = taskRepository.findAllByUserId(user.getId()).orElse(Collections.emptyList());
            List<String> completedTasks = userTasks.stream()
                    .filter(Task::getIsDone)
                    .map(Task::getHeader)
                    .toList();
            List<String> pendingTasks = userTasks.stream()
                    .filter(Task::getIsDone)
                    .map(Task::getHeader)
                    .toList();
            if (!completedTasks.isEmpty() && !pendingTasks.isEmpty()) {
                message = new Message(user.getEmail(), MESSAGE_HEADER, List.of(completedTasks, pendingTasks).toString());
            } else if (!completedTasks.isEmpty() && pendingTasks.isEmpty()) {
                message = new Message(user.getEmail(), generateCompleteHeader(String.valueOf(completedTasks.size())), List.of(completedTasks, pendingTasks).toString());
            } else {
                message = new Message(user.getEmail(), generatePendingHeader(String.valueOf(completedTasks.size())), List.of(completedTasks, pendingTasks).toString());
            }
            kafkaPoducer.sendMessage(message);
        });
    }

    public String generateCompleteHeader(String taskCount) {
        return "You have completed" + taskCount + "tasks today";
    }

    public String generatePendingHeader(String taskCount) {
        return "You have" + taskCount + "unfinished tasks left";
    }
}
