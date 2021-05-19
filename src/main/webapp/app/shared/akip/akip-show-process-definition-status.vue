<template>
  <div>
    <span class="badge rounded-pill bg-primary" v-if="status == 'ACTIVE'">{{ status }}</span>
    <span class="badge rounded-pill bg-secondary" v-else-if="status == 'INACTIVE'">{{ status }}</span>
    <span class="badge rounded-pill bg-danger" v-else-if="status == 'ERROR'">{{ status }}</span>
    <span class="badge rounded-pill bg-warning text-dark" v-else-if="status == 'NEW'">{{ status }}</span>
  </div>
</template>

<script lang="ts" src="./akip-show-process-definition-status.component.ts"></script>

<style scoped>
.badge {
  color: white;
  padding: 0.35em 0.65em;
  font-weight: bold;
  font-size: 90%;
}
</style>
