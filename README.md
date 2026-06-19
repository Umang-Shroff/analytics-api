# Analytics API

Spring Boot analytics service for a ClickHouse-powered event analytics platform inspired by CleverTap.

This repository exposes analytical REST APIs that power the dashboard layer by querying ClickHouse and pre-aggregated analytical tables.

---

## System Architecture

```text
┌──────────────────────────────┐
│      Event Ingestion         │
│      Java Event Pipeline     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│         ClickHouse           │
│   Events + Materialized      │
│           Views              │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│       Analytics API          │
│      (This Repository)       │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Analytics Dashboard     │
│        Vue + Tailwind        │
└──────────────────────────────┘
```

### Related Repositories

* [Event Ingestion](https://github.com/Umang-Shroff/data-collection-pipeline)
* [Analytics Dashboard](https://github.com/Umang-Shroff/event-dashboard)

---

## Responsibilities

This service acts as the analytical layer of the platform.

It is responsible for:

* Querying ClickHouse
* Serving dashboard APIs
* Aggregating analytics data
* Reading materialized views
* Revenue analytics
* Campaign analytics
* User analytics
* Tenant analytics
* Time-based analytics
* Partition analytics

---

## Architecture

```text
Controller Layer
        │
        ▼
Service Layer
        │
        ▼
Repository Layer
        │
        ▼
ClickHouse
```

* Controllers expose REST APIs.
* Services contain business logic.
* Repositories execute analytical queries.
* ClickHouse stores analytical data.

---

## API Endpoints

| Endpoint                     | Description               |
| ---------------------------- | ------------------------- |
| `/api/dashboard/overview`    | Overview metrics          |
| `/api/dashboard/event-types` | Event distribution        |
| `/api/dashboard/tenants`     | Tenant analytics          |
| `/api/dashboard/revenue`     | Revenue analytics         |
| `/api/dashboard/campaigns`   | Campaign analytics        |
| `/api/dashboard/hourly`      | Hourly event distribution |
| `/api/dashboard/partitions`  | Partition distribution    |
| `/api/dashboard/users`       | Top Users analytics       |
| `/api/dashboard/devices`     | Device analytics          |
| `/api/dashboard/health`      | Service health check      |

---

## Data Sources

The service reads data from:

* events
* event_type_counts
* tenant_event_counts
* revenue_stats
* campaign_stats

Materialized views are used to improve query performance and reduce full table scans.

---

## Technology Stack

* Java 21
* Spring Boot
* JDBC
* ClickHouse
* Maven

---

## Quick Start

```bash
git clone https://github.com/username/analytics-api

cd analytics-api

mvn clean install

mvn spring-boot:run
```

The API starts on:

```text
http://localhost:8080
```

---

## Example Request

```bash
curl http://localhost:8080/api/dashboard/overview
```

Example response:

```json
{
    "totalEvents": 1000,
    "totalUsers": 100,
    "totalTenants": 4,
    "totalRevenue": 52480
}
```
---

This repository serves as the analytical engine of the platform, transforming raw events into actionable insights for the dashboard layer.
