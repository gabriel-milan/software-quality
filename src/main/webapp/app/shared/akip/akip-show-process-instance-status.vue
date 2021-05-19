<template>
  <div>
    <span class="badge rounded-pill bg-success" v-if="status == 'RUNNING'">Running</span>
    <span class="badge rounded-pill bg-secondary" v-else-if="status == 'COMPLETED'">Completed</span>
    <span class="badge rounded-pill bg-danger" v-else-if="status == 'TERMINATED'">Terminated</span>
    <span class="badge rounded-pill bg-warning text-dark" v-else-if="status == 'CANCELED'">Canceled</span>
  </div>
</template>

<script lang="ts" src="./akip-show-process-instance-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}
</style>
