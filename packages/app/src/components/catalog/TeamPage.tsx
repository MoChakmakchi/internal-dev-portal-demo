import React from 'react';
import Grid from '@material-ui/core/Grid';
import { Header, GaugeCard, Table, TableColumn } from '@backstage/core-components';
import { makeStyles } from '@material-ui/core';

const teamColumns: TableColumn[] = [
{
  title: 'Team',
  field: 'team',
  highlight: true,
  render: e => <a href={e.teamlink}> {e.team} </a>,
},
{
  title: 'APIs',
  field: 'total',
  type: 'numeric',
  render: e => <a href={e.href}> {e.total} </a>,
},
{
  title: 'Services',
  field: 'services',
  type: 'numeric',
},
{
  title: 'Systems',
  field: 'systems',
  type: 'numeric',
},
{
  title: 'Members',
  field: 'members',
  type: 'numeric',
},
{
  title: 'Relationships',
  field: 'relationships',
  type: 'string',
  render: e => <a href={e.relationshipLink}> View </a>,
}
];

const generateTeamData: (number: number) => Array<{}> = (rows = 3) => {
  const data: Array<{}> = [];
    data.push({
      team: 'Health Team',
      total: 3,
      href: 'api-docs?filters%5Bkind%5D=api&filters%5Buser%5D=all&filters%5Bowners%5D=health-team',
      teamlink: 'catalog/default/group/health-team',
      services: 3,
      systems: 3,
      members: 1,
      relationshipLink: 'relationships/health'
    });
    data.push({
          team: `GI Team`,
        total: 0,
        href: '',
        teamlink: 'catalog/default/group/gi-team',
      services: 0,
      systems: 0,
      members: 0,
      relationshipLink: 'relationships/gi'
        });
    data.push({
          team: `Pension Team`,
        total: 2,
        href: 'api-docs?filters%5Bkind%5D=api&filters%5Buser%5D=all&filters%5Bowners%5D=pensions-team',
        teamlink: 'catalog/default/group/pensions-team',
      services: 2,
      systems: 1,
      members: 1,
      relationshipLink: 'relationships/pensions'
        });

  return data;
};

const teamData = generateTeamData(10);
export const TeamPage = () => {
  return (
    <Grid container rowSpacing={1} columnSpacing={2} alignItems="center" justifyContent="center">
    <Grid item md={12}>
    <Header title="Teams" />
    </Grid>
     <Grid item md={10}>
         <Table
           title="Teams"
           options={{ paging: false, search: false }}
           data={teamData}
           columns={teamColumns}
         />
      </Grid>

    </Grid>
  );
};