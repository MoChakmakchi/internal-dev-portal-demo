import React from 'react';
import Grid from '@material-ui/core/Grid';
import { Header, GaugeCard, Table, TableColumn } from '@backstage/core-components';
import AvivaLogo from '../Root/logo/aviva.png';
import { makeStyles } from '@material-ui/core';

const useLogoStyles = makeStyles({
  logoSize: {
    width: 'auto',
    height: '200px',
  },
});

export const Logo = () => {
  const logoStyle = useLogoStyles();
  return (
  <img src={AvivaLogo} className={logoStyle.logoSize} />
  );
};
const java8 = { title: 'Services ', link: '/catalog?filters%5Bkind%5D=component&filters%5Buser%5D=all&filters%5Btags%5D=java8' };
const java11 = { title: 'Services ', link: '/catalog?filters%5Bkind%5D=component&filters%5Buser%5D=all&filters%5Btags%5D=java11' };

const generateTestData: (number: number) => Array<{}> = (rows = 4) => {
  const data: Array<{}> = [];
    data.push({
      title: `Deployment Frequency`,
      number: Math.round(Math.abs(Math.sin(data.length)) * 1000)
    });
    data.push({
          title: `Lead Time for Changes`,
          number: Math.round(Math.abs(Math.sin(data.length)) * 1000)
        });
    data.push({
          title: `Mean Time to Recovery`,
          number: Math.round(Math.abs(Math.sin(data.length)) * 1000)
        });
    data.push({
          title: `Change Failure Rate`,
          number: Math.round(Math.abs(Math.sin(data.length)) * 1000)
        });

  return data;
};

const testData10 = generateTestData(10);

const columns: TableColumn[] = [
{
  title: 'Metric',
  field: 'title',
  highlight: true,
},
{
  title: 'Score',
  field: 'number',
  type: 'numeric',
}
];

const teamColumns: TableColumn[] = [
{
  title: 'Team',
  field: 'team',
  highlight: true,
},
{
  title: 'Migration Status',
  field: 'migration',
  type: 'numeric',
},
{
  title: 'API Lint Status',
  field: 'lint',
  type: 'numeric',
},
{
  title: 'Total APIs',
  field: 'total',
  type: 'numeric',
  render: e => <a href={e.href}> {e.total} </a>,
}
];

const generateTeamData: (number: number) => Array<{}> = (rows = 3) => {
  const data: Array<{}> = [];
    data.push({
      team: 'Health Team',
      migration: 3,
      lint: 10,
      total: 3,
      href: 'api-docs?filters%5Bkind%5D=api&filters%5Buser%5D=all&filters%5Bowners%5D=health-team'
    });
    data.push({
          team: `GI Team`,
        migration: 0,
        lint: 10,
        total: 0,
        href: ''
        });
    data.push({
          team: `Pension Team`,
        migration: 6,
        lint: 2,
        total: 2,
        href: 'api-docs?filters%5Bkind%5D=api&filters%5Buser%5D=all&filters%5Bowners%5D=pensions-team'
        });

  return data;
};

const teamData = generateTeamData(10);
export const HomePage = () => {
  const logoStyle = useLogoStyles();
  return (
    <Grid container rowSpacing={1} columnSpacing={2} alignItems="center" justifyContent="center">
    <Grid item md={12}>
    <Header title="Summary" />
    </Grid>
    <Grid item md={6}>
        <Table
        title="DORA Metrics"
           options={{ paging: false, search: false }}
          data={testData10}
          columns={columns}
        />
     </Grid>
     <Grid item md={6}>
      <Grid container rowSpacing={1} columnSpacing={2} justifyContent="center">
         <Grid item md={12}>
         <Header title="Migration" />
         </Grid>
         <Grid item md={6}>
                 <GaugeCard title="Gold Standard" subheader="Java 11" progress={0.40} description="Hover Message" deepLink={java11} />
          </Grid>
          <Grid item md={6}>
               <GaugeCard title="Off Track" subheader="Not Java 11" progress={0.60} description="Hover Message" deepLink={java8} />
           </Grid>
       </Grid>
     </Grid>
     <Grid item md={10}>
         <Table
           title="Team Leaderboard"
           options={{ paging: false, search: false }}
           data={teamData}
           columns={teamColumns}
         />
      </Grid>

    </Grid>
  );
};