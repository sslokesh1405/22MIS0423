public class vechile {
    package com.affordmed.vehicle_maintenance_scheduler.service;

import com.affordmed.vehicle_maintenance_scheduler.model.VehicleTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {

    public List<VehicleTask> optimizeTasks(
            List<VehicleTask> tasks,
            int maxHours
    ) {

        int n = tasks.size();

        int[][] dp =
                new int[n + 1][maxHours + 1];

        for (int i = 1; i <= n; i++) {

            VehicleTask task = tasks.get(i - 1);

            for (int w = 0; w <= maxHours; w++) {

                if (task.getDuration() <= w) {

                    dp[i][w] = Math.max(
                            task.getImpact()
                                    + dp[i - 1][w - task.getDuration()],
                            dp[i - 1][w]
                    );

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        List<VehicleTask> selectedTasks =
                new ArrayList<>();

        int w = maxHours;

        for (int i = n; i > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                VehicleTask task = tasks.get(i - 1);

                selectedTasks.add(task);

                w -= task.getDuration();
            }
        }

        return selectedTasks;
    }

    public int calculateTotalImpact(
            List<VehicleTask> tasks
    ) {

        int total = 0;

        for (VehicleTask task : tasks) {

            total += task.getImpact();
        }

        return total;
    }

    public int calculateTotalDuration(
            List<VehicleTask> tasks
    ) {

        int total = 0;

        for (VehicleTask task : tasks) {

            total += task.getDuration();
        }

        return total;
    }
}
}
