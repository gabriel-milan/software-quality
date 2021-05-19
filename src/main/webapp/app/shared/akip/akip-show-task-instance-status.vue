<template>
  <div>
    <span class="badge rounded-pill bg-success" v-if="status == 'NEW'">New</span>
    <span class="badge rounded-pill bg-primary" v-else-if="status == 'ASSIGNED'">Assigned</span>
    <span class="badge rounded-pill bg-secondary" v-else-if="status == 'COMPLETED'">Completed</span>
    <span class="badge rounded-pill bg-danger" v-else-if="status == 'UNASSIGNED'">Unassigned</span>
    <span class="badge rounded-pill bg-warning text-dark" v-else-if="status == 'DELEGATED'">Delegated</span>
  </div>
</template>

<script lang="ts" src="./akip-show-task-instance-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}
</style>
